package com.adsnet.voyage.objects;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum BusType {

	COASTER ("Coaster","COASTER"),
	GROS_PORTEUR ("Gros porteur","GROS_PORTEUR");
	
	private String name;
	
	private String value;
	
	BusType(String value, String name) {
	     this.value = value;
	     this.name=name;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
	
	
	
}
