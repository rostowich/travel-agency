package com.adsnet.subscription.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TopUpType {

	CREDIT ("Credit","CREDIT"),
	DEBIT("Retrait", "DEBIT");
	
	private String name;
	
	private String value;

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}
	
	private TopUpType(String name, String value) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.value=value;
	}
}
