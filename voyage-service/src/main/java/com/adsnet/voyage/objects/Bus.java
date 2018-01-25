package com.adsnet.voyage.objects;


public class Bus {

	private Long id;
	
	private String immatriculation;

	private BusType type;

	private String numberOfPlace;
	
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
