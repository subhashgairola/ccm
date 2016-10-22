package com.ccm.web.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartFile;

import com.ccm.excel.utils.CustomerDetail;
import com.ccm.excel.utils.ExcelRow;
import com.ccm.excel.utils.InvalidExcelException;

public interface CustomerService {

	List<ExcelRow> save(MultipartFile file, String sourceType) throws InvalidExcelException, DataAccessException, Exception;
	List<CustomerDetail> getCustomerDetails(int offset, int limit, String orderByCol, String sortOrder) throws DataAccessException;
	List<CustomerDetail> getCustomerDetailsWithSearchAndPage(int offset, int limit,
			String searchStr, String orderByCol, String sortOrder) throws DataAccessException;
	long getCountWithSearch(String searchStr) throws DataAccessException;
	long getTotalRecords() throws DataAccessException;
	void save(CustomerDetail customerDetail) throws DataAccessException;
}
