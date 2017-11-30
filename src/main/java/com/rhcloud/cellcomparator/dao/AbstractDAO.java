package com.rhcloud.cellcomparator.dao;

import java.util.Collection;
import java.util.List;

import com.rhcloud.cellcomparator.filter.Filter;

/**
 * Interface criada para fornecer metodos "padrão" de um DAO (Data Access Object).
 * <br><br>
 * Existe uma implementação padrão para ela chamada <code>AbstractDAOImpl</code>.
 * 
 * @author Estanislau Araújo
 * @since 19/12/2015
 * @param <E> entity
 */
public interface AbstractDAO<E> {
	
	void save(E e);
	
	void saveList(Collection<E> e);
	
	E update(E e);
	
	List<E> updateList(Collection<E> e);
	
	E findById(Class<E> e, Object id);
	
	Collection<E> findByFilter(Filter filter, String namedQuery, Boolean isDetach);
	
	E findByFilterUniqueResult(Filter filter, String namedQuery, Boolean isDetach);
	
	E findReference(Class<E> class1, Object id);
	
	void detach(E e);
	
	void detachList(Collection<E> e);
	
	void delete(E e);

}
