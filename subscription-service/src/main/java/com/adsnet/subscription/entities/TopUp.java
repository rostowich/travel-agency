package com.adsnet.subscription.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.adsnet.subscription.enums.TopUpType;

@Entity
@Table(name="tab_topup")
public class TopUp {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@Column(name = "AMOUNT")
	private String amount;
	
	@Column(name = "TOPUP_TYPE")
	@Enumerated(EnumType.STRING)
	private TopUpType topUpType;
	
	@ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
	private Account account;
	
	@Column(name = "CREATION_DATE")
	private Date creationDate;
	
	@Transient
	private java.sql.Date creationDateBegin;
	
	@Transient
	private java.util.Date creationDateEnd;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public TopUpType getTopUpType() {
		return topUpType;
	}

	public void setTopUpType(TopUpType topUpType) {
		this.topUpType = topUpType;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public java.sql.Date getCreationDateBegin() {
		return creationDateBegin;
	}

	public void setCreationDateBegin(java.sql.Date creationDateBegin) {
		this.creationDateBegin = creationDateBegin;
	}

	public java.util.Date getCreationDateEnd() {
		return creationDateEnd;
	}

	public void setCreationDateEnd(java.util.Date creationDateEnd) {
		this.creationDateEnd = creationDateEnd;
	}

	@Override
	public String toString() {
		return "TopUp [id=" + id + ", amount=" + amount + ", topUpType=" + topUpType + ", account=" + account
				+ ", creationDate=" + creationDate + ", creationDateBegin=" + creationDateBegin + ", creationDateEnd="
				+ creationDateEnd + "]";
	}

	public TopUp(String amount, TopUpType topUpType, Account account, Date creationDate) {
		super();
		this.amount = amount;
		this.topUpType = topUpType;
		this.account = account;
		this.creationDate = creationDate;
	}

	public TopUp() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
