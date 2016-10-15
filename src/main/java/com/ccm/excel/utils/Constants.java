package com.ccm.excel.utils;


public class Constants {
	public static final String NAV_SOURCE_TYPE = "NAV";
	public static final String APSIS_SOURCE_TYPE = "APSIS";
	public static final String ZENDESK_SOURCE_TYPE = "Zendesk";
	public static final String MAGENTO_SOURCE_TYPE = "Magento";
	public static final String REEDERID_SOURCE_TYPE = "ReederID";
	
	public static final String SOURCE = "source";
	public static final String COL_ID = "id";
	public static final String COL_NAME = "name";
	public static final String COL_MOBILE_NUM = "mobileNum";
	public static final String COL_EMAIL = "email";
	public static final String COL_CREATION_DATE = "creationDate";
	public static final String COL_PHONE_NO = "phoneNum";
	public static final String COL_LAST_LOGIN_DATE = "lastLogin";
	public static final String COL_GENDER = "gender";
	public static final String COL_ZIP = "zip";
	public static final String COL_COUNTRY = "country";
	public static final String COL_PASSWORD = "password";
	public static final String COL_BIRTH_DATE = "birthDate";
	public static final String COL_IP_ADDRESS = "ipAddress";
	public static final String COL_LOCATION = "location";
	public static final String COL_STATE_NAME = "stateName";
	public static final String COL_IS_NEWS = "isNews";
	
	//Source	ID	Name/Surname	Email	CreationDate
	public static final String[] APSIS_COLUMNS = {"Source", "ID", "Name/Surname",	"Email", "CreationDate"}; 
	
	//Source	ID	Name/Surname	Phone	Location
	public static final String[] NAV_COLUMNS = {"Source", "ID", "Name/Surname",	"Phone", "Location"}; 
	
	//Source	ID	Name/Surname	Email	Phone	CreationDate	Password	Sex	isNews	Location	IP	Birthday
	public static final String[] REEDER_COLUMNS = {"Source", "ID", "Name/Surname", "Email",	"Phone", "CreationDate", "Password", "Sex", "isNews", "Location", "IP", "Birthday"}; 
	
	//Source	ID	Name/Surname	Email	CreationDate	Last Login
	public static final String[] ZENDESK_COLUMNS = {"Source", "ID", "Name/Surname",	"Email", "CreationDate", "Last Login"};
	
	//Source	ID	Name/Surname	Email	Phone	CreationDate	ZIP	Country	State
	public static final String[] MAGENTO_COLUMNS = {"Source", "ID", "Name/Surname",	"Email", "Phone","CreationDate", "ZIP","Country", "State"};
	
	
		
}
