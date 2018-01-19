package com.adsnet.referential.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tab_partner")
public class Partner {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@Column(name = "LABEL")
	private String label;
	
	@Column(name = "LOGIN")
	private String login;
	
	@JsonIgnore
	@Column(name = "password")
	private String password;

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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Partner [id=" + id + ", label=" + label + ", login=" + login + ", password=" + password + "]";
	}

	public Partner(Long id, String label, String login, String password) {
		super();
		this.id = id;
		this.label = label;
		this.login = login;
		this.password = password;
	}

	public Partner() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
