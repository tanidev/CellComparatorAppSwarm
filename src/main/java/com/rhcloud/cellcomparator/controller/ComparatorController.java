package com.rhcloud.cellcomparator.controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.rhcloud.cellcomparator.cdi.qualifier.RankingDAO;
import com.rhcloud.cellcomparator.cdi.qualifier.SmartphoneDAO;
import com.rhcloud.cellcomparator.dao.impl.AbstractDAOImpl;
import com.rhcloud.cellcomparator.dao.impl.SmartphoneDAOImpl;
import com.rhcloud.cellcomparator.dto.ResultCompare;
import com.rhcloud.cellcomparator.entity.Characteristic;
import com.rhcloud.cellcomparator.entity.CharacteristicsType;
import com.rhcloud.cellcomparator.entity.Ranking;
import com.rhcloud.cellcomparator.entity.Result;
import com.rhcloud.cellcomparator.entity.Smartphone;
import com.rhcloud.cellcomparator.filter.Filter;

@ViewScoped
@Transactional(value=TxType.REQUIRED)
public class ComparatorController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4964082695694017640L;
	
	@Inject @SmartphoneDAO
	private AbstractDAOImpl<Smartphone> smartphoneDAOImpl;
	
	@Inject @RankingDAO
	private AbstractDAOImpl<Ranking> rankingDAOImpl;
	
	private Map<CharacteristicsType, Characteristic> characteristicsSmartphone1 = new LinkedHashMap<>();
	private Map<CharacteristicsType, Characteristic> characteristicsSmartphone2 = new LinkedHashMap<>();
	
	@PostConstruct
	public void init() {
		for(CharacteristicsType type : CharacteristicsType.values()) {
			characteristicsSmartphone1.put(type, null);
			characteristicsSmartphone2.put(type, null);
		}
	}

	public List<Smartphone> populateSmartphones() {
		return ((SmartphoneDAOImpl) smartphoneDAOImpl).findAll();
	}
	
	public Collection<Smartphone> findByFilter(Filter filter) {
		return smartphoneDAOImpl.findByFilter(filter, Smartphone.FIND_BY_NAME_AND_MANUFACTURER, Boolean.TRUE);
	}
	
	public ResultCompare compare(List<Smartphone> smartphoneToCompare) {
			
			smartphoneToCompare.get(0).getCharacteristics().stream()
				.forEach(c -> characteristicsSmartphone1.put(c.getType(), c));
			
			smartphoneToCompare.get(1).getCharacteristics().stream()
				.forEach(c -> characteristicsSmartphone2.put(c.getType(), c));
			
		
		return process(smartphoneToCompare);
	}
	
	public Ranking favorite(Smartphone smartphone) {
		
		Ranking ranking = new Ranking(
				smartphoneDAOImpl.findReference(Smartphone.class, smartphone.getSmartphoneId()), new Date());
		
		rankingDAOImpl.save(ranking);
		return ranking;
	}
	
	private ResultCompare process(List<Smartphone> smartphones) {
		
		for(CharacteristicsType type : CharacteristicsType.values()) {
			
			Characteristic c1 = characteristicsSmartphone1.get(type);
			Characteristic c2 = characteristicsSmartphone2.get(type);
			
			if(!type.isComparable() && c1 != null && c2 != null) {
				c1.setResult(Result.NOT_COMPARABLE);
				c2.setResult(Result.NOT_COMPARABLE);
				continue;
			}
			
			if(c1 == null && c2 == null) {
				
				c1 = new Characteristic(null, null, type, null);
				c1.setResult(Result.NOT_SUPPORTED);
				c2 = new Characteristic(null, null, type, null);
				c2.setResult(Result.NOT_SUPPORTED);
				
				characteristicsSmartphone1.put(type, c1);
				characteristicsSmartphone2.put(type, c2);
				
			} else if (c1 == null && c2 != null) {
				
				c1 = new Characteristic(null, null, type, null);
				c1.setResult(Result.NOT_SUPPORTED);
				c2.setResult(Result.WINNER);
				
				characteristicsSmartphone1.put(type, c1);
				
			} else if (c1 != null && c2 == null) {
				
				c2 = new Characteristic(null, null, type, null);
				c2.setResult(Result.NOT_SUPPORTED);
				c1.setResult(Result.WINNER);
				
				characteristicsSmartphone2.put(type, c2);
				
			} else {

				int result = type.getComparator().compare(c1, c2);
				
				switch (result) {
				case 0:
					c1.setResult(Result.DRAW);
					c2.setResult(Result.DRAW);
					break;
				case 1:
					c1.setResult(Result.WINNER);
					c2.setResult(Result.LOOSER);
					break;
				case -1:
					c1.setResult(Result.LOOSER);
					c2.setResult(Result.WINNER);
					break;
				}
			}
			
		}
		
		return new ResultCompare(smartphones.get(0), characteristicsSmartphone1.values(), 
				smartphones.get(1), characteristicsSmartphone2.values());
		
	}
}
