package com.adsnet.referential.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="tab_cash_register")
public class CashRegister {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@NotEmpty
	@Column(name = "LABEL")
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
		return "CashRegister [id=" + id + ", label=" + label + "]";
	}

	public CashRegister(Long id, String label) {
		super();
		this.id = id;
		this.label = label;
	}

	public CashRegister() {
		super();
		// TODO Auto-generated constructor stub
	}

}
