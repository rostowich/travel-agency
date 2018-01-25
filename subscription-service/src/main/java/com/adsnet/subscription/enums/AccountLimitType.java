package com.adsnet.subscription.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AccountLimitType {

	DAILY_LIMIT ("Journaliere","DAILY_LIMIT"),
	WEEKLY_LIMIT ("Hebdomadaire", "WEEKLY_LIMIT"),
	MONTHLY_LIMIT ("Mensuelle","MONTHLY_LIMIT");
	
	private String name;
	
	private String value;
	
	private AccountLimitType(String name, String value) {
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
