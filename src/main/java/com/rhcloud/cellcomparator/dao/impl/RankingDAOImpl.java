package com.rhcloud.cellcomparator.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import com.rhcloud.cellcomparator.cdi.qualifier.RankingDAO;
import com.rhcloud.cellcomparator.dto.RankingResultDTO;
import com.rhcloud.cellcomparator.entity.Ranking;
import com.rhcloud.cellcomparator.filter.RankingFilter;

@Stateless
@RankingDAO
public class RankingDAOImpl extends AbstractDAOImpl<Ranking> {
	
	/**
	 * Método que realiza a pesquisa de acordo com os filtro de Ranking enviado.
	 * 
	 * @see <class>RankingType.java</class>
	 * @param filter
	 * @return Uma lista de RankingResultDTO para o relatório.
	 */
	@SuppressWarnings("unchecked")
	public List<RankingResultDTO> findByChart(RankingFilter filter) {
		
		Query query = em.createNamedQuery(Ranking.FIND_BY_CHART);
		query.setParameter("initialDate", filter.getInitialDate(), TemporalType.DATE);
		query.setParameter("finalDate", filter.getFinalDate(), TemporalType.DATE);
		query.setMaxResults(5);
		
		return (List<RankingResultDTO>) query.getResultList();
		
	}

}
