package com.adsnet.subscription.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tab_souscription")
public class Souscription {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "LOGIN")
	private String login;
	
	@Column(name = "PIN_CODE")
	private String pinCode;
	
	@Column(name = "COMPLETE_NAME")
	private String completeName;
	
	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	@Column(name = "CARD_NUMBER_IDENTIFIER")
	private String cardNumberIdentifier;
	
	@Column(name = "ADRESS")
	private String address;
	
	@Column(name = "CREATION_DATE")
	private Date creationDate;
	
	@Column(name = "MODIFICATION_DATE")
	private Date modificationDate;
	
	@ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
	private Account account;


	@Override
	public String toString() {
		return "Souscription [id=" + id + ", email=" + email + ", pinCode=" + pinCode + ", completeName=" + completeName
				+ ", phoneNumber=" + phoneNumber + ", cardNumberIdentifier=" + cardNumberIdentifier + ", adress="
				+ address + ", creationDate=" + creationDate + ", account=" + account + "]";
	}




	public Date getCreationDate() {
		return creationDate;
	}




	public Date getModificationDate() {
		return modificationDate;
	}




	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}




	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}




	public String getLogin() {
		return login;
	}




	public void setLogin(String login) {
		this.login = login;
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getPinCode() {
		return pinCode;
	}




	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}




	public String getCompleteName() {
		return completeName;
	}




	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}




	public String getPhoneNumber() {
		return phoneNumber;
	}




	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}




	public String getCardNumberIdentifier() {
		return cardNumberIdentifier;
	}




	public void setCardNumberIdentifier(String cardNumberIdentifier) {
		this.cardNumberIdentifier = cardNumberIdentifier;
	}




	public String getAddress() {
		return address;
	}




	public void setAddress(String adress) {
		this.address = adress;
	}




	public Account getAccount() {
		return account;
	}




	public void setAccount(Account account) {
		this.account = account;
	}




	public Souscription() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
