package com.adsnet.referential.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tab_bus_station")
public class BusStation {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@Column(name = "LABEL")
	private String label;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "BusStation [id=" + id + ", label=" + label + "]";
	}

	public BusStation(Long id, String label) {
		super();
		this.id = id;
		this.label = label;
	}

	public BusStation() {
		super();
	}
	
	
}
