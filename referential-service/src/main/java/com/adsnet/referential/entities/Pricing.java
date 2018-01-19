package com.adsnet.referential.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.adsnet.referential.enums.TravelType;

@Entity
@Table(name="tab_pricing")
public class Pricing {
	
	@Id
	@Column(name = "ID", nullable = false)
	private String id;
	
	@Column(name = "travel_type")
	@Enumerated(EnumType.STRING)
	private TravelType travelType;
	
	@Column(name = "amount")
	private String amount;
	
	@ManyToOne
    @JoinColumn(name = "class_id")
	private Classe classe;

	public String getId() {
		return id;
	}

	public void setId(TravelType travelType, Classe classe) {
		this.id = travelType.getValue()+"_"+classe.getId();
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public TravelType getTravelType() {
		return travelType;
	}

	public void setTravelType(TravelType travelType) {
		this.travelType = travelType;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	@Override
	public String toString() {
		return "Pricing [id=" + id + ", travelType=" + travelType + ", amount=" + amount + ", classe=" + classe + "]";
	}

	public Pricing(String id, TravelType travelType, String amount, Classe classe) {
		super();
		this.id = id;
		this.travelType = travelType;
		this.amount = amount;
		this.classe = classe;
	}

	public Pricing() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
