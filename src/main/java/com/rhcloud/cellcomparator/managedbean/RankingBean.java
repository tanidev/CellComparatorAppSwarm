package com.rhcloud.cellcomparator.managedbean;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.rhcloud.cellcomparator.cdi.qualifier.RankingDAO;
import com.rhcloud.cellcomparator.dao.impl.AbstractDAOImpl;
import com.rhcloud.cellcomparator.dao.impl.RankingDAOImpl;
import com.rhcloud.cellcomparator.dto.RankingResultDTO;
import com.rhcloud.cellcomparator.entity.Ranking;
import com.rhcloud.cellcomparator.filter.RankingFilter;

@Named
@ViewScoped
public class RankingBean implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 814773368419566007L;
	
	@Inject @RankingDAO
	private AbstractDAOImpl<Ranking> rankingDAOImpl;
	
	private RankingType rankingType = RankingType.TODAY;
	private List<RankingResultDTO> rankingResultDTO;
	private BarChartModel barModel;
	
	@PostConstruct
	public void init() {
		
		rankingResultDTO = ((RankingDAOImpl) rankingDAOImpl).findByChart(new RankingFilter(new Date(), new Date()));
		barModel = null;
		
		if(!rankingResultDTO.isEmpty()) {
			barModel = new BarChartModel();
			rankingResultDTO.forEach(r -> {
				
				ChartSeries chart = new ChartSeries(r.getSmartphoneName());
				chart.set("Top 5", r.getQuantity());
				
				barModel.addSeries(chart);
			});
			
			barModel.setLegendPosition("ne");
		}
		
	}
	
	public void findByRankingType() {
		System.out.println("Refresh chart...");
		rankingResultDTO = ((RankingDAOImpl) rankingDAOImpl).findByChart(rankingType.getRankingFilter());
		barModel = null;
		
		if(!rankingResultDTO.isEmpty()) {
			barModel = new BarChartModel();
			rankingResultDTO.forEach(r -> {
				
				ChartSeries chart = new ChartSeries(r.getSmartphoneName());
				chart.set("Top 5", r.getQuantity());
				
				barModel.addSeries(chart);
			});
			
			barModel.setLegendPosition("ne");
		}
		
	}
	
	public BarChartModel getBarModel() {
		return barModel;
	}
	
	public List<RankingResultDTO> getRankingResultDTO() {
		return rankingResultDTO;
	}
	
	public RankingType getRankingType() {
		return rankingType;
	}
	
	public void setRankingType(RankingType rankingType) {
		this.rankingType = rankingType;
	}
	
	public RankingType[] getRankingTypes() {
		return RankingType.values();
	}
	
	public enum RankingType {
		
		MONTH("Ranking por mês"){
			@Override
			public RankingFilter getRankingFilter() {
				
				LocalDate initial = LocalDate.now();
				LocalDate start = initial.withDayOfMonth(1);
				LocalDate end = initial.withDayOfMonth(initial.lengthOfMonth());
				
				return new RankingFilter(Date.from(start.atStartOfDay().atZone(ZoneId.of("America/Sao_Paulo")).toInstant()), 
						Date.from(end.atStartOfDay().atZone(ZoneId.of("America/Sao_Paulo")).toInstant()));
			}
		}, 
		
		WEEK("Ranking por semana") {
			@Override
			public RankingFilter getRankingFilter() {
				
				LocalDate initial = LocalDate.now();
				TemporalField fieldWeek = WeekFields.of(new Locale("pt", "BR")).dayOfWeek();
				
				return new RankingFilter(Date.from(initial.with(fieldWeek, DayOfWeek.MONDAY.getValue()).
						atStartOfDay().atZone(ZoneId.of("America/Sao_Paulo")).toInstant()), 
							Date.from(initial.with(fieldWeek, DayOfWeek.SATURDAY.getValue()).
						atStartOfDay().atZone(ZoneId.of("America/Sao_Paulo")).toInstant()));
			}
		},
		
		TODAY("Ranking de hoje") {
			@Override
			public RankingFilter getRankingFilter() {
				return new RankingFilter(new Date(), new Date());
			}
		};
		
		private String type;
		
		RankingType(String type) {
			this.type = type;
		}
		
		public abstract RankingFilter getRankingFilter();
		
		public String getType() {
			return type;
		}
		
	}
	
	
}
