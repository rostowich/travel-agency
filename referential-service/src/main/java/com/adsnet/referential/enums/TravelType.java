package com.adsnet.referential.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TravelType {

	ONE_WAY_TRAVEL ("Aller-simple", "ONE_WAY_TRAVEL"),
	TWO_WAY_TRAVEL("Aller-retour", "TWO_WAY_TRAVEL");
	
	private String name;
	
	private String value;
	
	private TravelType(String name, String value) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.value=value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}
	
}
