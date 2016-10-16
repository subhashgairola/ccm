package com.ccm.web.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ccm.excel.utils.CustomerDetail;
import com.ccm.excel.utils.ExcelRow;

public interface CustomerDao {

	void save(List<ExcelRow> rows, String sourceSystem) throws DataAccessException;
	List<CustomerDetail> getCustomerDetails(int offset, int limit);
	List<CustomerDetail> getCustomerDetailsWithSearchAndPage(int offset,
			int limit, String searchStr) throws DataAccessException;
	long getCustomerDetails(String searchStr) throws DataAccessException;
	long getTotalRecords() throws DataAccessException;
	void save(CustomerDetail customerDetail) throws DataAccessException;

}
