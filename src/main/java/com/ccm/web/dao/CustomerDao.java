package com.ccm.web.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ccm.excel.utils.CustomerDetail;
import com.ccm.excel.utils.ExcelRow;

public interface CustomerDao {

	List<ExcelRow> saveRows(List<ExcelRow> rows, String sourceSystem) throws DataAccessException;
	List<CustomerDetail> getCustomerDetails(int offset, int limit, String orderByCol, String sortOrder);
	List<CustomerDetail> getCustomerDetailsWithSearchAndPage(int offset,
			int limit, String searchStr, String orderByCol, String sortOrder) throws DataAccessException;
	long getCountBySearch(String searchStr) throws DataAccessException;
	long getTotalRecords() throws DataAccessException;
	void save(CustomerDetail customerDetail) throws DataAccessException;
	boolean isCustomerDuplicate(String paramType, String param, int cusDetailId);

}
