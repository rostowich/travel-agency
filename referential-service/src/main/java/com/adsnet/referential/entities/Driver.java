package com.adsnet.referential.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="tab_driver")
public class Driver {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@Column(name = "DRIVER_LICENCE_NUMBER", unique=true)
	private String driverLicenceNumber;
	
	@Column(name = "DRIVER_LICENCE_DELIVERY_DATE")
	private Date driverLicenceDeliveryDate;
	
	@Transient
	private Date driverLicenceDeliveryBeginDate;
	
	@Transient
	private Date driverLicenceDeliveryEndDate;
	
	@Column(name = "DRIVER_LICENCE_EXPIRATION_DATE")
	private Date driverLicenceExpirationDate;
	
	@Transient
	private Date driverLicenceExpirationBeginDate;
	
	@Transient
	private Date driverLicenceExpirationEndDate;
	
	@Column(name = "FULL_NAME")
	private String fullName;
	
	@Column(name = "ID_CARD_NUMBER")
	private String idCardNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDriverLicenceNumber() {
		return driverLicenceNumber;
	}

	public void setDriverLicenceNumber(String driverLicenceNumber) {
		this.driverLicenceNumber = driverLicenceNumber;
	}

	public Date getDriverLicenceDeliveryDate() {
		return driverLicenceDeliveryDate;
	}

	public void setDriverLicenceDeliveryDate(Date driverLicenceDeliveryDate) {
		this.driverLicenceDeliveryDate = driverLicenceDeliveryDate;
	}

	public Date getDriverLicenceExpirationDate() {
		return driverLicenceExpirationDate;
	}

	public void setDriverLicenceExpirationDate(Date driverLicenceExpirationDate) {
		this.driverLicenceExpirationDate = driverLicenceExpirationDate;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getIdCardNumber() {
		return idCardNumber;
	}

	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}
	
	

	public Date getDriverLicenceDeliveryBeginDate() {
		return driverLicenceDeliveryBeginDate;
	}

	public void setDriverLicenceDeliveryBeginDate(Date driverLicenceDeliveryBeginDate) {
		this.driverLicenceDeliveryBeginDate = driverLicenceDeliveryBeginDate;
	}

	public Date getDriverLicenceDeliveryEndDate() {
		return driverLicenceDeliveryEndDate;
	}

	public void setDriverLicenceDeliveryEndDate(Date driverLicenceDeliveryEndDate) {
		this.driverLicenceDeliveryEndDate = driverLicenceDeliveryEndDate;
	}

	public Date getDriverLicenceExpirationBeginDate() {
		return driverLicenceExpirationBeginDate;
	}

	public void setDriverLicenceExpirationBeginDate(Date driverLicenceExpirationBeginDate) {
		this.driverLicenceExpirationBeginDate = driverLicenceExpirationBeginDate;
	}

	public Date getDriverLicenceExpirationEndDate() {
		return driverLicenceExpirationEndDate;
	}

	public void setDriverLicenceExpirationEndDate(Date driverLicenceExpirationEndDate) {
		this.driverLicenceExpirationEndDate = driverLicenceExpirationEndDate;
	}

	@Override
	public String toString() {
		return "Driver [id=" + id + ", driverLicenceNumber=" + driverLicenceNumber + ", driverLicenceDeliveryDate="
				+ driverLicenceDeliveryDate + ", driverLicenceExpirationDate=" + driverLicenceExpirationDate
				+ ", fullName=" + fullName + ", idCardNumber=" + idCardNumber + "]";
	}

	public Driver(String driverLicenceNumber, Date driverLicenceDeliveryDate, Date driverLicenceExpirationDate,
			String fullName, String idCardNumber) {
		super();
		this.driverLicenceNumber = driverLicenceNumber;
		this.driverLicenceDeliveryDate = driverLicenceDeliveryDate;
		this.driverLicenceExpirationDate = driverLicenceExpirationDate;
		this.fullName = fullName;
		this.idCardNumber = idCardNumber;
	}

	public Driver() {
		super();
		// TODO Auto-generated constructor stub
	}

}
