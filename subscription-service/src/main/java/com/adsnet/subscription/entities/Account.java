package com.adsnet.subscription.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.adsnet.subscription.enums.AccountLimitType;
import com.adsnet.subscription.enums.AccountType;

@Entity
@Table(name="tab_account")
public class Account {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@Column(name = "LABEL")
	private String label;
	
	@Column(name = "NUMBER")
	private String number;
	
	@Column(name = "BALANCE")
	private Double balance;
	
	@Column(name = "ACCOUNT_TYPE")
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	
	@Column(name = "ACCOUNT_LIMIT_TYPE")
	@Enumerated(EnumType.STRING)
	private AccountLimitType accountLimitType;
	
	@Column(name = "AMOUNT_LIMIT_TYPE")
	private String amountLimit;
	
	@Column(name = "CREATION_DATE")
	private Date creationDate;
	
	@Column(name = "MODIFICATION_DATE")
	private Date modificationDate;

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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public AccountLimitType getAccountLimitType() {
		return accountLimitType;
	}

	public void setAccountLimitType(AccountLimitType accountLimitType) {
		this.accountLimitType = accountLimitType;
	}

	public String getAmountLimit() {
		return amountLimit;
	}

	public void setAmountLimit(String amountLimit) {
		this.amountLimit = amountLimit;
	}
	
	

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	

	@Override
	public String toString() {
		return "Account [id=" + id + ", label=" + label + ", number=" + number + ", balance=" + balance
				+ ", accountType=" + accountType + ", accountLimitType=" + accountLimitType + ", amountLimit="
				+ amountLimit + ", creationDate=" + creationDate + ", modificationDate=" + modificationDate + "]";
	}

	public Account(Long id, String label, String number, Double balance, AccountType accountType,
			AccountLimitType accountLimitType, String amountLimit) {
		super();
		this.id = id;
		this.label = label;
		this.number = number;
		this.balance = balance;
		this.accountType = accountType;
		this.accountLimitType = accountLimitType;
		this.amountLimit = amountLimit;
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
