package com.ccm.web.dao;

import java.util.List;

import com.ccm.excel.utils.CustomerDetail;
import com.ccm.excel.utils.ExcelRow;

public interface CustomerDao {

	void save(List<ExcelRow> rows, String sourceSystem);
	List<CustomerDetail> getCustomerDetails(int offset, int limit);
	List<CustomerDetail> getCustomerDetailsWithSearchAndPage(int offset,
			int limit, String searchStr);
	long getCustomerDetails(String searchStr);
	long getTotalRecords();
	void save(CustomerDetail customerDetail);

}
