package com.rhcloud.cellcomparator.cdi.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * Produtor padrão de um EntityManagerFactory.
 * <br><br>
 * <b>Utilizar somente em contexto de classes DAO's e afins.</b>
 * 
 * @author Estanislau Araújo
 * @since 02/02/2015
 */
@ApplicationScoped
class EntityManagerProducer {
	
	@PersistenceUnit(unitName="CellComparatorDS")
	private EntityManagerFactory emf;
	
	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {
		
		return emf.createEntityManager();
		
	}
	
	public void fecha(@Disposes EntityManager em) {
		em.close();
	}
	
//	@Produces @ApplicationScoped
//	public Cache getCache() {
//		
//		return emf.getCache();
//		
//	}

}
