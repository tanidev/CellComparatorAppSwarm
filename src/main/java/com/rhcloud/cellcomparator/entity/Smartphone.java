package com.rhcloud.cellcomparator.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries(value={
		@NamedQuery(name=Smartphone.FIND_ALL, query="SELECT DISTINCT s FROM Smartphone s "
				+ "LEFT JOIN FETCH s.characteristics"),
		@NamedQuery(name=Smartphone.FIND_BY_NAME_AND_MANUFACTURER, query="SELECT DISTINCT s FROM Smartphone s "
				+ "LEFT JOIN FETCH s.characteristics "
				+ "WHERE (:smartphoneNameIsNull IS TRUE OR lower(s.name) like :smartphoneName) AND "
				+ "(:manufacturerIsNull IS TRUE OR s.manufacturer = :manufacturer)")
})
public class Smartphone implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4552172693963281640L;
	
	/*
	 * NAMED QUERIES
	 */
	public static final String FIND_ALL = "Smartphone.findAll";
	public static final String FIND_BY_NAME_AND_MANUFACTURER = "Smartphone.findByNameAndManufacturer";

	@Id
	@GeneratedValue
	private Integer smartphoneId;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String imageName;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private Manufacturer manufacturer;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="smartphone")
	private List<Characteristic> characteristics;
	
	/**
	 * FIX JPA
	 */
	Smartphone() {
		
	}
	
	/**
	 * Construtor com campos obrigatórios
	 * 
	 * @param name
	 * @param imageBytes
	 * @param manufacturer
	 * @param characteristics
	 */
	public Smartphone(String name, String imageName, Manufacturer manufacturer) {
		this.name = name;
		this.imageName = imageName;
		this.manufacturer = manufacturer;		
	}

	/**
	 * @return the smartphoneId
	 */
	public Integer getSmartphoneId() {
		return smartphoneId;
	}

	/**
	 * @param smartphoneId the smartphoneId to set
	 */
	public void setSmartphoneId(Integer smartphoneId) {
		this.smartphoneId = smartphoneId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * @param imageName the imageName to set
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	/**
	 * @return the manufacturer
	 */
	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	/**
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * @return the characteristics
	 */
	public List<Characteristic> getCharacteristics() {
		return characteristics;
	}

	/**
	 * @param characteristics the characteristics to set
	 */
	public void setCharacteristics(List<Characteristic> characteristics) {
		this.characteristics = characteristics;
	}
	
	@Override
	public int hashCode() {
		
		int result = 31;
		
		result *= name.hashCode() >>> 2;
		result *= manufacturer.hashCode() >>> 2;
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj == this) {
			return true;
		}
		
		if(!(obj instanceof Smartphone)) {
			return false;
		}
		
		Smartphone smartphone = (Smartphone) obj;
		
		return smartphone.getName().equals(name) &&
				smartphone.getManufacturer().equals(manufacturer);
		
	}
	
	@Override
	public String toString() {
		
		return name;
	}

}
