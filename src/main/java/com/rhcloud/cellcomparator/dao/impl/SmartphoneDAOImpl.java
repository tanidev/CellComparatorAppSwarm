package com.rhcloud.cellcomparator.dao.impl;

import java.util.List;

import javax.ejb.Stateless;

import com.rhcloud.cellcomparator.cdi.qualifier.SmartphoneDAO;
import com.rhcloud.cellcomparator.entity.Smartphone;

@Stateless
@SmartphoneDAO
public class SmartphoneDAOImpl extends AbstractDAOImpl<Smartphone> {
	
	/**
	 * Método que realiza a pesquisa de todos os smartphones cadastrados.
	 * @return Uma lista em estado detached de Smartphones
	 */
	public List<Smartphone> findAll() {
		return findByFiltersDetached(null, Smartphone.FIND_ALL);
	}

}
