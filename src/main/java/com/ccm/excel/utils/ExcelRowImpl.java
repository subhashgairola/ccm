package com.ccm.excel.utils;

import java.sql.Timestamp;
import java.util.Date;

public class ExcelRowImpl implements ExcelRow {
	private int customerDetailId;
	private String source;
	private String id;
	private String name;
	private String password;
	private String email;
	private String jobTitle;
	private String company;
	private String mobileNum;
	private String birthDate;
	private String gender;
	private String creationDate;
	private String ipAddress;
	private String country;
	private String city;
	private int updatedBy;
	private String zip;
	private String phoneNum;
	private String location;
	private String lastLogin;
	
	private Timestamp insertedDate;
	private Timestamp updateDate;
	
	private int stateId;
	private String stateName;
	private String isNews;

	public ExcelRowImpl() {
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getId() {
		return id;
	}
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
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

	public Timestamp getInsertedDate() {
		return insertedDate;
	}

	public void setInsertedDate(Timestamp insertedDate) {
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

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	
	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public int getCustomerDetailId() {
		return customerDetailId;
	}

	public void setCustomerDetailId(int customerDetailId) {
		this.customerDetailId = customerDetailId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	public String getIsNews() {
		return isNews;
	}

	public void setIsNews(String isNews) {
		this.isNews = isNews;
	}
	@Override
	public String toString() {
		return "ExcelRowImpl [customerDetailId=" + customerDetailId
				+ ", source=" + source + ", id=" + id + ", name=" + name
				+ ", password=" + password + ", email=" + email + ", jobTitle="
				+ jobTitle + ", company=" + company + ", mobileNum="
				+ mobileNum + ", birthDate=" + birthDate + ", gender=" + gender
				+ ", creationDate=" + creationDate + ", ipAddress=" + ipAddress
				+ ", country=" + country + ", city=" + city + ", updatedBy="
				+ updatedBy + ", zip=" + zip + ", phoneNum=" + phoneNum
				+ ", location=" + location + ", lastLogin=" + lastLogin
				+ ", insertedDate=" + insertedDate + ", updateDate="
				+ updateDate + ", stateId=" + stateId + ", stateName="
				+ stateName + "]";
	}

	
    
}