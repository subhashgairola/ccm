package com.ccm.web.dao;

import java.util.List;

import com.ccm.excel.utils.CustomerDetail;
import com.ccm.excel.utils.ExcelRow;

public interface CustomerDao {

	void save(List<ExcelRow> rows, String sourceSystem);
	List<CustomerDetail> getCustomerDetails();

}
