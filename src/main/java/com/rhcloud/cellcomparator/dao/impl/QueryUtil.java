package com.rhcloud.cellcomparator.dao.impl;

import java.util.Collection;
/**
 * Classe utilitária para queries utilizando o padrão AbstractDAOImpl.
 * 
 * @author Estanislau Araújo
 * @since 22/12/2015
 *
 */
public class QueryUtil {
	
	public static boolean isNull(Object obj) {

		if (obj == null) {
			return true;
		}

		if (obj instanceof String) {
			String s = (String) obj;
			if (s.trim().equals("")) {
				return true;
			}
		}
		
		if (obj instanceof Integer) {
			Integer i = (Integer) obj;
			if (i.equals(0)) {
				return true;
			}
		}
		
		if (obj instanceof Double) {
			Double i = (Double) obj;
			if (i.equals(0.0d)) {
				return true;
			}
		}

		if (obj instanceof Collection) {

			@SuppressWarnings("rawtypes")
			Collection col = (Collection) obj;
			if (col.isEmpty()) {
				return true;
			}
		}

		return false;

	}
	
	public static String ilike(String field) {
		
		return field != null ? "%" + field.toLowerCase() + "%" : field;
		
	}

}
