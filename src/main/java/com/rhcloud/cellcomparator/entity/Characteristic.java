package com.rhcloud.cellcomparator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Characteristic {
	
	@Id
	@GeneratedValue
	private Integer characteristicsId;
	
	@Column(nullable=false)
	private String value;
	
	@Column
	private String description;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private CharacteristicsType type;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Smartphone smartphone;
	
	@Transient
	private Result result;
	
	/**
	 * FIX JPA
	 */
	Characteristic() {
	
	}

	/**
	 * Construtor com campos obrigatórios
	 * 
	 * @param type
	 * @param value
	 */
	public Characteristic(String value, String description, CharacteristicsType type, Smartphone smartphone) {
		this.value = value;
		this.description = description;
		this.type = type;
		this.smartphone = smartphone;
	}

	/**
	 * @return the characteristicsId
	 */
	public Integer getCharacteristicsId() {
		return characteristicsId;
	}

	/**
	 * @param characteristicsId the characteristicsId to set
	 */
	public void setCharacteristicsId(Integer characteristicsId) {
		this.characteristicsId = characteristicsId;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the type
	 */
	public CharacteristicsType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(CharacteristicsType type) {
		this.type = type;
	}

	/**
	 * @return the smartphone
	 */
	public Smartphone getSmartphone() {
		return smartphone;
	}

	/**
	 * @param smartphone the smartphone to set
	 */
	public void setSmartphone(Smartphone smartphone) {
		this.smartphone = smartphone;
	}

	/**
	 * @return the result
	 */
	public Result getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(Result result) {
		this.result = result;
	}
	
	@Override
	public int hashCode() {
		
		int result = 31;
		
		result *= value.hashCode() >>> 2;
		result *= type.hashCode() >>> 2;
		result *= smartphone.hashCode() >>> 2;
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj == this) {
			return true;
		}
		
		if(!(obj instanceof Characteristic)) {
			return false;
		}
		
		Characteristic characteristic = (Characteristic) obj;
		
		return characteristic.getValue().equals(value) && characteristic.getType().equals(type) &&
				characteristic.getSmartphone().equals(smartphone);
		
	}
	
	@Override
	public String toString() {
		return "Type=" + type + ", value=" + value;
	}
}
	