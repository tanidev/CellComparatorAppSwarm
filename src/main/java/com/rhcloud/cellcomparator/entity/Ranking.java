package com.rhcloud.cellcomparator.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries(value={
		@NamedQuery(name=Ranking.FIND_BY_CHART, query="SELECT NEW com.rhcloud.cellcomparator.dto.RankingResultDTO("
				+ "count(r.smartphone), r.smartphone.name) FROM Ranking r "
				+ "WHERE cast(r.dateOfFavorite as date) BETWEEN :initialDate AND :finalDate "
				+ "GROUP BY r.smartphone.name ORDER BY count(r.smartphone) desc")
})
public class Ranking implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3979752159162076403L;
	
	/*
	 * NAMED QUERIES
	 */
	public static final String FIND_BY_CHART = "Ranking.findByChart";
	
	@Id
	@GeneratedValue
	private Integer rankingId;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Smartphone smartphone;
	
	@Column(nullable=false)
	private Date dateOfFavorite;
	
	/**
	 * FIX JPA
	 */
	Ranking() {
		
	}
	
	/**Construtor com campos obrigatórios
	 * 
	 * @param smartphone
	 * @param dateOfFavorite
	 */
	public Ranking(Smartphone smartphone, Date dateOfFavorite) {
		this.smartphone = smartphone;
		this.dateOfFavorite = dateOfFavorite;
	}
	
	/**
	 * @return the rankingId
	 */
	public Integer getRankingId() {
		return rankingId;
	}

	/**
	 * @param rankingId the rankingId to set
	 */
	public void setRankingId(Integer rankingId) {
		this.rankingId = rankingId;
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
	 * @return the dateOfFavorite
	 */
	public Date getDateOfFavorite() {
		return dateOfFavorite;
	}

	/**
	 * @param dateOfFavorite the dateOfFavorite to set
	 */
	public void setDateOfFavorite(Date dateOfFavorite) {
		this.dateOfFavorite = dateOfFavorite;
	}

	@Override
	public int hashCode() {
		
		int result = 31;
		
		result *= rankingId.hashCode() >>> 2;
		result *= smartphone.hashCode() >>> 2;
		result *= dateOfFavorite.hashCode() >>> 2;
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj == this) {
			return true;
		}
		
		if(!(obj instanceof Ranking)) {
			return false;
		}
		
		Ranking ranking = (Ranking) obj;
		
		return ranking.getRankingId().equals(rankingId) && ranking.getSmartphone().equals(smartphone) &&
				ranking.getDateOfFavorite().equals(dateOfFavorite);
		
	}

}
