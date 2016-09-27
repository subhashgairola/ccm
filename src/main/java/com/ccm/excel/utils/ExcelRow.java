package com.ccm.excel.utils;

import java.sql.Date;
import java.sql.Timestamp;

public interface ExcelRow {
	public String getClientId();

	public String getClientName();

	public String getPassword();

	public String getEmail();

	public String getMobileNum();

	public Date getBirthDate();

	public String getGender();

	public Date getCreationDate();

	public String getIpAddress();

	public String getCountry();

	public Date getInsertedDate();

	public Timestamp getUpdateDate();

	public int getUpdatedBy();

	public String getSourceSystem();

}
