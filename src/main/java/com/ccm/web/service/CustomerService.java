package com.ccm.web.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ccm.excel.utils.CustomerDetail;
import com.ccm.excel.utils.InvalidExcelException;

public interface CustomerService {

	void save(MultipartFile file, String sourceType) throws InvalidExcelException, Exception;
	List<CustomerDetail> getCustomerDetails(int offset, int limit);
	List<CustomerDetail> getCustomerDetailsWithSearchAndPage(int offset, int limit,
			String searchStr);
	long getCustomerDetails(String searchStr);
	long getTotalRecords();
	void save(CustomerDetail customerDetail);
}
