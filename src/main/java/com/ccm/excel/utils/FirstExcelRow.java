package com.ccm.excel.utils;

public class FirstExcelRow implements ExcelRow {
	private String clientId;
	private String clientName;
	private String clientDetails;

	public FirstExcelRow() {
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

	public String getClientDetails() {
		return clientDetails;
	}

	public void setClientDetails(String clientDetails) {
		this.clientDetails = clientDetails;
	}

	@Override
	public String toString() {
		return "Book [clientId=" + clientId + ", clientName=" + clientName
				+ ", clientDetails=" + clientDetails + "]";
	}
}