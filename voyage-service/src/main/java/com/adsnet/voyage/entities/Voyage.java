package com.adsnet.voyage.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tab_voyage")
public class Voyage {

	@Id
	@Column(name = "ID", nullable = false)
	private String id;
	
	@Column(name = "DEPARTURE_DATE")
	private Date departureDate;
	
	@Column(name = "NUMBER_OF_PLACE")
	private int numberOfPlace;
	
	@Column(name = "NUMBER_SALE_PLACE")
	private int numberOfSalePlace;
	
	@Column(name = "BOOL_ARCHIVED")
	private Boolean  archived;
	
    @Column(name = "DEPARTURE_HOUR_ID")
	private Long departureHourId;
	
    @Column(name = "BUS_ID", nullable = true)
	private Long busId;
	
	@Column(name = "PATH_ID")
	private String pathId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public int getNumberOfPlace() {
		return numberOfPlace;
	}

	public void setNumberOfPlace(int numberOfPlace) {
		this.numberOfPlace = numberOfPlace;
	}

	public int getNumberOfSalePlace() {
		return numberOfSalePlace;
	}

	public void setNumberOfSalePlace(int numberOfSalePlace) {
		this.numberOfSalePlace = numberOfSalePlace;
	}

	public Boolean getArchived() {
		return archived;
	}

	public void setArchived(Boolean archived) {
		this.archived = archived;
	}

	public Long getDepartureHourId() {
		return departureHourId;
	}

	public void setDepartureHourId(Long departureHourId) {
		this.departureHourId = departureHourId;
	}

	public Long getBusId() {
		return busId;
	}

	public void setBusId(Long busId) {
		this.busId = busId;
	}

	public String getPathId() {
		return pathId;
	}

	public void setPath(String pathId) {
		this.pathId = pathId;
	}

	@Override
	public String toString() {
		return "Voyage [id=" + id + ", departureDate=" + departureDate + ", numberOfPlace=" + numberOfPlace
				+ ", numberOfSalePlace=" + numberOfSalePlace + ", archived=" + archived + ", departureHourId="
				+ departureHourId + ", busId=" + busId + ", pathId=" + pathId + "]";
	}

	public Voyage(Date departureDate, int numberOfPlace, int numberOfSalePlace, Boolean archived,
			Long departureHourId, Long busId, String path) {
		super();
		this.departureDate = departureDate;
		this.numberOfPlace = numberOfPlace;
		this.numberOfSalePlace = numberOfSalePlace;
		this.archived = archived;
		this.departureHourId = departureHourId;
		this.busId = busId;
		this.pathId = path;
	}

	public Voyage() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
