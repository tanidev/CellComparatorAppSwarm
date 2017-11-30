package com.rhcloud.cellcomparator.cdi.producer;

import java.util.Iterator;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.util.AnnotationLiteral;
import javax.naming.InitialContext;
import javax.naming.NamingException;


/**
 * Localizador de Beans dentro do contexto CDI.
 * 
 * Caso não seja possivel fazer uma injeção de dependencias via anotações como é o padrão deste projeto,
 * use esta classe para localizar o bean atraves de seu .class e da sua anotação de qualificador.
 * 
 * @author Estanislau Araújo
 * @since 12/19/2015
 */
public class CDILocator {
	
	private  BeanManager getBeanManager(){
		try {
			return (BeanManager) InitialContext.doLookup("java:comp/BeanManager");
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Retorna uma instancia gerada pelo contexto do servidor de um Bean gerenciavel pelo CDI.
	 * 
	 * @param clazz
	 * @param literal
	 * @return Uma instancia de um bean gerenciavel.
	 */
    public <T> T lookup(final Class<T> clazz, AnnotationLiteral<?> literal) {
        final BeanManager bm = getBeanManager();
        return lookup(clazz, literal, bm);
    }

    @SuppressWarnings({ "serial" })
	public <T> T lookup(final Class<T> clazz, BeanManager bm) {
    	return lookup(clazz, new AnnotationLiteral<Default>(){}, bm);
    }

    @SuppressWarnings("unchecked")
	public <T> T lookup(final Class<T> clazz, AnnotationLiteral<?> literal, BeanManager bm) {
        final Iterator<Bean<?>> iter = bm.getBeans(clazz, literal).iterator();
        if (!iter.hasNext()) {
            throw new IllegalStateException("CDI BeanManager cannot find an instance of requested type " + clazz.getName());
        }
        final Bean<T> bean = (Bean<T>) iter.next();
        final CreationalContext<T> ctx = bm.createCreationalContext(bean);
        return (T) bm.getReference(bean, clazz, ctx);
    }
}
