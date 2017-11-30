package com.rhcloud.cellcomparator.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.rhcloud.cellcomparator.controller.ComparatorController;
import com.rhcloud.cellcomparator.dto.ResultCompare;
import com.rhcloud.cellcomparator.entity.Manufacturer;
import com.rhcloud.cellcomparator.entity.Ranking;
import com.rhcloud.cellcomparator.entity.Smartphone;
import com.rhcloud.cellcomparator.filter.SmartphoneFilter;

@Named
@ViewScoped
public class ComparatorBean implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -726697637346179129L;
	
	@Inject
	private ComparatorController comparatorController;
	
	private SmartphoneFilter filter = new SmartphoneFilter();
	private Collection<Smartphone> smartphones;
	
	private Smartphone selectedSmartphone;
	private List<Smartphone> smartphonesToCompare = new ArrayList<>();
	
	private ResultCompare resultCompare;
	private Ranking ranking;
	
	@PostConstruct
	public void init() {
		smartphones = comparatorController.populateSmartphones();
	}
	
	public void find() {
		smartphones = comparatorController.findByFilter(filter);
	}
	
	public void addToCompare(ActionEvent event) {
		
		
		smartphonesToCompare.add((Smartphone)event.getComponent().getAttributes().get("sp"));
		System.out.println(smartphonesToCompare);
		
		if(smartphonesToCompare.size() == 2) {
			
			resultCompare = comparatorController.compare(smartphonesToCompare);
			RequestContext.getCurrentInstance().execute("PF('wizardVar').next()");
			
		}
	}
	
	public void favorite(Smartphone smartphone) {
		
		ranking = comparatorController.favorite(smartphone);
		
	}
	
	public SmartphoneFilter getFilter() {
		return filter;
	}
	
	public void setFilter(SmartphoneFilter filter) {
		this.filter = filter;
	}
	
	public Collection<Smartphone> getSmartphones() {
		return smartphones;
	}
	
	public Smartphone getSelectedSmartphone() {
		return selectedSmartphone;
	}
	
	public void setSelectedSmartphone(Smartphone selectedSmartphone) {
		this.selectedSmartphone = selectedSmartphone;
	}
	
	public List<Smartphone> getSmartphonesToCompare() {
		return smartphonesToCompare;
	}
	
	public ResultCompare getResultCompare() {
		return resultCompare;
	}
	
	public Ranking getRanking() {
		return ranking;
	}
	
	public Manufacturer[] getManufacturers() {
		return Manufacturer.values();
	}
	
}
