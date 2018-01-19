package com.adsnet.referential.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="tab_path")
public class Path {

	@Id
	@Column(name = "ID", nullable = false)
	private String id;
	
	@Transient
	private String label;
	
	@ManyToOne
    @JoinColumn(name = "to_go_city_id")
	private City departure;
	
	@ManyToOne
    @JoinColumn(name = "to_back_city_id")
	private City destination;

	public String getId() {
		return id;
	}

	public void setId(City departure, City destination) {
		if(this.departure!=null && this.destination!=null)
		this.id = departure.getId()+"_"+destination.getId();
	}

	public void setId(String id) {
		this.id=id;
	}
	
	public String getLabel() {
		if(departure!=null && destination!=null)
		return departure.getLabel()+" - "+destination.getLabel();
		else return null;
	}
	
	public City getDeparture() {
		return departure;
	}

	public void setDeparture(City departure) {
		this.departure = departure;
	}

	public City getDestination() {
		return destination;
	}

	public void setDestination(City destination) {
		this.destination = destination;
	}

	

	@Override
	public String toString() {
		return "Path [id=" + id + ", label=" + label + ", departure=" + departure + ", destination=" + destination
				+ "]";
	}

	public Path(String id, String label, City departure, City destination) {
		super();
		this.id = id;
		this.label = label;
		this.departure = departure;
		this.destination = destination;
	}

	public Path() {
		super();
	}
	
	
}
