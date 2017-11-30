package com.rhcloud.cellcomparator.filter;

import java.util.HashMap;
import java.util.Map;

import com.rhcloud.cellcomparator.dao.impl.QueryUtil;
import com.rhcloud.cellcomparator.entity.Manufacturer;

public class SmartphoneFilter implements Filter {
	
	private String smartphoneName;
	private Manufacturer manufacturer;
	
	@Override
	public Map<String, Object> getMapParam() {
		
		Map<String, Object> params = new HashMap<>();
		
		params.put("smartphoneName", QueryUtil.ilike(smartphoneName));
		params.put("smartphoneNameIsNull", QueryUtil.isNull(smartphoneName));
		params.put("manufacturer", manufacturer);
		params.put("manufacturerIsNull", QueryUtil.isNull(manufacturer));
		
		return params;
	}
	
	public String getSmartphoneName() {
		return smartphoneName;
	}
	
	public void setSmartphoneName(String smartphoneName) {
		this.smartphoneName = smartphoneName;
	}
	
	public Manufacturer getManufacturer() {
		return manufacturer;
	}
	
	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

}
