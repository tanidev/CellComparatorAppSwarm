package com.rhcloud.cellcomparator.dto;

import java.io.Serializable;
import java.util.Collection;

import com.rhcloud.cellcomparator.entity.Characteristic;
import com.rhcloud.cellcomparator.entity.Smartphone;

public class ResultCompare implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8284159103203034736L;
	
	private final Smartphone smartphone1;
	private final Smartphone smartphone2;
	private final Collection<Characteristic> comparableCharacteristic1;
	private final Collection<Characteristic> comparableCharacteristic2;
	
	public ResultCompare(Smartphone smartphone1, Collection<Characteristic> comparableCharacteristic1, 
			Smartphone smartphone2, Collection<Characteristic> comparableCharacteristic2) {
		this.smartphone1 = smartphone1;
		this.comparableCharacteristic1 = comparableCharacteristic1;
		this.smartphone2 = smartphone2;
		this.comparableCharacteristic2 = comparableCharacteristic2;
	}
	
	public Smartphone getSmartphone1() {
		return smartphone1;
	}
	
	public Collection<Characteristic> getComparableCharacteristic1() {
		return comparableCharacteristic1;
	}
	
	public Smartphone getSmartphone2() {
		return smartphone2;
	}
	
	public Collection<Characteristic> getComparableCharacteristic2() {
		return comparableCharacteristic2;
	}
	

}
