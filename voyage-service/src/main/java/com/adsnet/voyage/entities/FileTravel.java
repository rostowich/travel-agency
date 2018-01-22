package com.adsnet.voyage.entities;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tab_travel_file")
public class FileTravel {

	@Id
	@Column(name = "ID", nullable = false)
	private String id;
	
	@Column(name = "FILE_NAME")
	private String fileName;
	
	@Column(name = "FILE_TYPE")
	private String fileType;
	
	@Column(name = "FILE_SIZE")
	private int fileSize;
	
	@Column(name = "LOAD_DATE")
	private Date loadDate;
	
	@Column(name = "BEGIN_DATE")
	private java.sql.Date beginDate;
	
	@Column(name = "END_DATE")
	private java.sql.Date endDate;
	
	@Column(name = "NUMBER_OF_VOYAGE")
	private int NumberOfVoyage;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public Date getLoadDate() {
		return loadDate;
	}

	public void setLoadDate(Date loadDate) {
		this.loadDate = loadDate;
	}

	public java.sql.Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(java.sql.Date beginDate) {
		this.beginDate = beginDate;
	}

	public java.sql.Date getEndDate() {
		return endDate;
	}

	public void setEndDate(java.sql.Date endDate) {
		this.endDate = endDate;
	}

	public int getNumberOfVoyage() {
		return NumberOfVoyage;
	}

	public void setNumberOfVoyage(int numberOfVoyage) {
		NumberOfVoyage = numberOfVoyage;
	}

	public FileTravel() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "FileTravel [id=" + id + ", fileName=" + fileName + ", fileType=" + fileType + ", fileSize=" + fileSize
				+ ", loadDate=" + loadDate + ", beginDate=" + beginDate + ", endDate=" + endDate + ", NumberOfVoyage="
				+ NumberOfVoyage + "]";
	}
	
}
