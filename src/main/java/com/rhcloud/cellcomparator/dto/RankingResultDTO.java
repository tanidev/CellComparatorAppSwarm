package com.rhcloud.cellcomparator.dto;

import java.io.Serializable;

public class RankingResultDTO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1414039988877537508L;
	
	private Long quantity;
	private String smartphoneName;
	
	public RankingResultDTO(Long quantity, String smartphoneName) {
		this.quantity = quantity;
		this.smartphoneName = smartphoneName;
	}
	
	public Long getQuantity() {
		return quantity;
	}
	
	public String getSmartphoneName() {
		return smartphoneName;
	}

}
