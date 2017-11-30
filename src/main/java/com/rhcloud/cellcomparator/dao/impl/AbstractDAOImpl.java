package com.rhcloud.cellcomparator.dao.impl;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import com.rhcloud.cellcomparator.dao.AbstractDAO;
import com.rhcloud.cellcomparator.filter.Filter;

/**
 * Classe abstrata que fornece uma implementação generica para os DAO's representados
 * por uma entidade. Para os metodos "padrão" (persist, merge, remove, find, findReference e detach).
 * <br><br>
 * Util para evitar codigo duplicado em cada classe DAO criada.
 * <br><br>
 * Esta classe, deve ser sempre estendida por um EJB anotado com <code>@Stateless</code>.
 * 
 * @author Estanislau Araújo
 * @since 19/12/2015
 * @param <E> <code>entidade</code>
 */

@Default
public abstract class AbstractDAOImpl<E> implements AbstractDAO<E> {

	@Inject
	EntityManager em;
	
	/**
	 * Persiste um objeto na base de dados.
	 * @see <class>EntityManager.persist</class>
	 */
	@Override
	public void save(E e) {
		em.joinTransaction();
		em.persist(e);
		
	}
	
	/**
	 * Persiste uma lista de objetos na base de dados.
	 * @see <class>EntityManager.persist</class>
	 */
	@Override
	public void saveList(Collection<E> e) {
		em.joinTransaction();
		for(E i : e) {
			em.persist(i);
		}
	}

	/**
	 * Realiza o merge de um objeto na base de dados.
	 * 
	 * @see <class>EntityManager.merge</class>
	 * @return Um objeto atualizado em estado <b>Managed</b>.
	 */
	@Override
	public E update(E e) {
		em.joinTransaction();
		return em.merge(e);
		
	}
	
	/**
	 * Realiza o merge de uma lista de objetos na base de dados.
	 * 
	 * @see <class>EntityManager.merge</class>
	 * @return Um lista de objetos atualizados em estado <b>Managed</b>.
	 */
	@Override
	public List<E> updateList(Collection<E> e) {
		List<E> list = new ArrayList<E>();
		em.joinTransaction();
		for(E i : e) {
			list.add(em.merge(i));
		}
		return list;
	}
	
	/**
	 * Realiza a pesquisa por id.
	 * 
	 * @see <class>EntityManager.find</class>
	 * @return Um objeto em estado <b>Managed</b>.
	 */
	@Override
	public E findById(Class<E> e, Object id) {
		try {
			return em.find(e, id);
		} catch (NoResultException nre) {
			return null;
		}
		
	}
	
	/**
	 * Realiza a pesquisa por id.
	 * 
	 * @see <class>EntityManager.getReference</class>
	 * @return Um objeto apenas com o id populado em estado <b>Managed</b>.
	 */
	@Override
	public E findReference(Class<E> e, Object id) {
		
		return em.getReference(e, id);
		
	}
	
	/**
	 * Método genérico que retorna uma coleção de objetos de acordo com os parametros enviados pela classe que implementa
	 * <class>Filter.java</class>
	 * 
	 * @param filter
	 * @param namedQuery
	 * @param isDetach
	 * @return Uma lista de objetos podem estar em estado <b>Managed</b> ou <b>Detach</b>.
	 */
	@Override
	public Collection<E> findByFilter(Filter filter, String namedQuery, Boolean isDetach) {
		
		return isDetach == Boolean.TRUE ? findByFiltersDetached(filter.getMapParam(), namedQuery) :
			findByFilters(filter.getMapParam(), namedQuery);
	}
	
	/**
	 * Método genérico que retorna um objeto de acordo com os parametros enviados pela classe que implementa
	 * <class>Filter.java</class>
	 * 
	 * @param filter
	 * @param namedQuery
	 * @param isDetach
	 * @return Um objeto podendo estar em estado <b>Managed</b> ou <b>Detach</b>.
	 */
	@Override
	public E findByFilterUniqueResult(Filter filter, String namedQuery, Boolean isDetach) {
		
		return isDetach == Boolean.TRUE ? findByFiltersUniqueResultDetached(filter.getMapParam(), namedQuery) :
			findByFiltersUniqueResult(filter.getMapParam(), namedQuery);
	}
	
	/**
	 * Transforma o estado do objeto para <b>Detach</b>.
	 * @see <class>EntityManager.detach</class>
	 */
	@Override
	public void detach(E e) {
		
		em.detach(e);
		
	}
	
	/**
	 * Transforma o estado de uma lista de objetos para <b>Detach</b>.
	 * @see <class>EntityManager.detach</class>
	 */
	@Override
	public void detachList(Collection<E> e) {
		
		for(E i : e) {
			em.detach(i);
		}
		
	}
	
	/**
	 * Transforma o estado do objeto para <b>Removed</b> e após o commit remove o registro da base de dados.
	 * @see <class>EntityManager.remove</class>
	 */
	@Override
	public void delete(E e) {
		
		em.joinTransaction();
		em.remove(e);
		
	}
	
	/**
	 * Método genérico de pesquisa que recebe um mapa de parametros da query com os valores.
	 * 
	 * @param params
	 * @param namedQuery
	 * @return Uma lista de objetos em estado <b>Managed</b>.
	 */
	@SuppressWarnings("unchecked")
	List<E> findByFilters(Map<String, Object> params, String namedQuery) {
		
		try {
			
			Query query = em.createNamedQuery(namedQuery);
			query = setParameters(query, params);
			
			return query.getResultList();
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			return null;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	/**
	 * Método genérico de pesquisa que recebe um mapa de parametros da query com os valores.
	 * 
	 * @param params
	 * @param namedQuery
	 * @return Uma lista de objetos em estado <b>Detach</b>.
	 */
	List<E> findByFiltersDetached(Map<String, Object> params, String namedQuery) {
		
		List<E> listEntity = findByFilters(params, namedQuery);
		
		if(listEntity != null && !listEntity.isEmpty()) {
			detachList(listEntity);
		}
		
		return listEntity;
	}
	
	/**
	 * Método genérico de pesquisa que recebe um mapa de parametros da query com os valores.
	 * 
	 * @param params
	 * @param namedQuery
	 * @return Um objeto em estado <b>Managed</b>.
	 */
	@SuppressWarnings("unchecked")
	E findByFiltersUniqueResult(Map<String, Object> params, String namedQuery) {
		
		try {
			
			Query query = em.createNamedQuery(namedQuery);
			query = setParameters(query, params);
			
			return (E) query.getSingleResult();
			
		} catch (NoResultException nre) {
			return null;
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			return null;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * Método genérico de pesquisa que recebe um mapa de parametros da query com os valores.
	 * 
	 * @param params
	 * @param namedQuery
	 * @return Um objeto em estado <b>Detach</b>.
	 */
	E findByFiltersUniqueResultDetached(Map<String, Object> params, String namedQuery) {
		
		E entity = findByFiltersUniqueResult(params, namedQuery);
		
		if(entity != null) {
			detach(entity);
		}
		
		return entity;
		
	}
	
	/**
	 * Método auxiliar para setar os parametros enviados.
	 * Possui uma inteligencia para validar se o parametro esta ou não na namedQuery.
	 * 
	 * <b>Para parametros de DATA, já faz o CAST para DATE, evitando problemas de query.</b>
	 * @param query
	 * @param params
	 * @return Um objeto Query com os parametros setados.
	 */
	private Query setParameters(Query query, Map<String, Object> params) {
		
		Set<String> parametersName = query.getParameters().stream()
				.map((p) -> p.getName())
				.collect(Collectors.toSet());
		
		if(params != null) {
		
			params.entrySet().stream()
				.filter((e) -> parametersName.contains(e.getKey()))
				.forEach((paramsEntry) -> {
					if(paramsEntry.getValue() instanceof Instant) {
						query.setParameter(paramsEntry.getKey(),(Date) paramsEntry.getValue(), TemporalType.DATE);
					} else {
						query.setParameter(paramsEntry.getKey(), paramsEntry.getValue());
					}
				});
		
		}
		
		return query;
		
	}

}