package com.ccm.excel.utils;

import java.sql.Timestamp;
import java.util.Date;

public interface ExcelRow {
	public String getClientId();

	public String getClientName();

	public String getPassword();

	public String getEmail();

	public String getMobileNum();

	public String getBirthDate();

	public String getGender();

	public Date getCreationDate();

	public String getIpAddress();

	public String getCountry();

	public Timestamp getInsertedDate();

	public Timestamp getUpdateDate();

	public int getUpdatedBy();

	public String getSourceSystem();

	public String getCity();
	
	public String getZip();
	
	public String getLocation();
	
	public String getPhoneNum();
	
	public Date getLastLogin();

}
