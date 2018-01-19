package com.adsnet.referential.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.adsnet.referential.enums.BusType;

@Entity
@Table(name="tab_bus")
public class Bus {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@Column(name = "IMMATRICULATION", unique=true)
	private String immatriculation;
	
	@Column(name = "TYPE")
	@Enumerated(EnumType.STRING)
	private BusType type;
	
	@Column(name = "NBR_PLACES")
	private String numberOfPlace;
	
	@Column(name = "MARK")
	private String mark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BusType getType() {
		return type;
	}

	public void setType(BusType type) {
		this.type = type;
	}

	public String getNumberOfPlace() {
		return numberOfPlace;
	}

	public void setNumberOfPlace(String numberOfPlace) {
		this.numberOfPlace = numberOfPlace;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	

	@Override
	public String toString() {
		return "Bus [id=" + id + ", immatriculation=" + immatriculation + ", type=" + type + ", numberOfPlace="
				+ numberOfPlace + ", mark=" + mark + "]";
	}

	public Bus(Long id, String immatriculation, BusType type, String numberOfPlace, String mark) {
		super();
		this.id = id;
		this.immatriculation = immatriculation;
		this.type = type;
		this.numberOfPlace = numberOfPlace;
		this.mark = mark;
	}

	public Bus() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
