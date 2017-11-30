package com.rhcloud.cellcomparator.filter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RankingFilter implements Filter {

	private Date initialDate;
	private Date finalDate;
	
	public RankingFilter(Date initialDate, Date finalDate) {
		this.initialDate = initialDate;
		this.finalDate = finalDate;
	}
	
	@Override
	public Map<String, Object> getMapParam() {
		
		Map<String, Object> params = new HashMap<>();
		params.put("initialDate", initialDate);
		params.put("finalDate", finalDate);
		
		return params;
	}
	
	public Date getInitialDate() {
		return initialDate;
	}
	
	public Date getFinalDate() {
		return finalDate;
	}

}
