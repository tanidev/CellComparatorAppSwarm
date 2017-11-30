package com.rhcloud.cellcomparator.filter;

import java.util.Map;

/**
 * Interface que define o contrato de utilização dos filtros.
 * Utilizada para implementar um filtro genérico nas pesquisas de seus respectivos DAO's.
 * 
 * @see <class>AbstractDAOImpl.java</class>
 * @author Estanislau Araújo
 * @since 22/12/2015
 *
 */
public interface Filter {
	
	Map<String, Object> getMapParam();

}
