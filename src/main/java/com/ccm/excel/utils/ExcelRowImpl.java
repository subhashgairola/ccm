package com.ccm.excel.utils;

import java.sql.Date;
import java.sql.Timestamp;

public class ExcelRowImpl implements ExcelRow {
	private String clientId;
	private String clientName;
	private String password;
	private String email;
	private String mobileNum;
	private Date birthDate;
	private String gender;
	private Date creationDate;
	private String ipAddress;
	private String country;
	private String city;
	private Date insertedDate;
	private Timestamp updateDate;
	private int updatedBy;
	private String sourceSystem;

	public ExcelRowImpl() {
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getInsertedDate() {
		return insertedDate;
	}

	public void setInsertedDate(Date insertedDate) {
		this.insertedDate = insertedDate;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getSourceSystem() {
		return sourceSystem;
	}

	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	@Override
	public String toString() {
		return "ExcelRowImpl [clientId=" + clientId + ", clientName="
				+ clientName + ", password=" + password + ", email=" + email
				+ ", mobileNum=" + mobileNum + ", birthDate=" + birthDate
				+ ", gender=" + gender + ", creationDate=" + creationDate
				+ ", ipAddress=" + ipAddress + ", country=" + country
				+ ", city=" + city + ", insertedDate=" + insertedDate
				+ ", updateDate=" + updateDate + ", updatedBy=" + updatedBy
				+ ", sourceSystem=" + sourceSystem + "]";
	}
}