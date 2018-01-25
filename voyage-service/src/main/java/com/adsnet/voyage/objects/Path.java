package com.adsnet.voyage.objects;

public class Path {

	private String id;
	
	private String label;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Path() {
		super();
	}

	public Path(String id, String label) {
		super();
		this.id = id;
		this.label = label;
	}
	
}
