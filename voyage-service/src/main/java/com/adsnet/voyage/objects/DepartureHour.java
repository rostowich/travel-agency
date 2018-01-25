package com.adsnet.voyage.objects;


public class DepartureHour {
		
	private Long id;
	
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
		return "DepartureHour [id=" + id + ", label=" + label + "]";
	}

	public DepartureHour(Long id, String label) {
		super();
		this.id = id;
		this.label = label;
	}

	public DepartureHour() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
