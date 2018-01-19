package com.adsnet.referential.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="tab_class")
public class Classe {

	@Id
	@Column(name = "ID", nullable = false)
	private String id;
	
	@Column(name = "LABEL")
	private String label;
	
	@Transient
	private String longLabel;
	
	@ManyToOne
    @JoinColumn(name = "path_id")
	private Path path;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void setId(Path path, String label) {
		this.id = this.label+"_"+path.getId();
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLongLabel() {
		return label+" ("+path.getLabel()+")";
	}

	public void setLongLabel(String longLabel) {
		this.longLabel = longLabel;
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "Class [id=" + id + ", label=" + label + ", longLabel=" + longLabel + ", path=" + path + "]";
	}

	public Classe(String id, String label, String longLabel, Path path) {
		super();
		this.id = id;
		this.label = label;
		this.longLabel = longLabel;
		this.path = path;
	}

	public Classe() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
