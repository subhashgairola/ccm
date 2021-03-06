package com.ccm.excel.utils;

import java.sql.Timestamp;
import java.util.Date;

public interface ExcelRow {
	public String getId();

	public String getSource();

	public String getName();

	public String getPassword();

	public String getEmail();

	public String getJobTitle();

	public String getCompany();

	public String getMobileNum();

	public String getBirthDate();

	public String getGender();

	public String getCreationDate();

	public String getIpAddress();

	public String getCountry();

	public int getUpdatedBy();

	public String getCity();

	public String getZip();

	public String getLocation();

	public String getPhoneNum();

	public String getLastLogin();

	public Timestamp getInsertedDate();

	public Timestamp getUpdateDate();
	
	public int getStateId();
	
	public String getStateName();
	
	public String getIsNews();
	
}
