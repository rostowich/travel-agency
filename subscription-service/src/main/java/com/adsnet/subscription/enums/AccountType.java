package com.adsnet.subscription.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AccountType {

	DEPOSIT ("Depot","DEPOSIT"),
	CREDIT ("Credit", "CREDIT");
	
	private String name;
	
	private String value;
	
	AccountType(String name, String value){
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
