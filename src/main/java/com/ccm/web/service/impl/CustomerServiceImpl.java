package com.ccm.web.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ccm.excel.utils.CustomerDetail;
import com.ccm.excel.utils.ExcelRow;
import com.ccm.excel.utils.ExcelUtil;
import com.ccm.web.dao.CustomerDao;
import com.ccm.web.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Resource
	private CustomerDao customerDao;

	@Override
	public void save(MultipartFile file, String sourceType) throws IOException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		InputStream is = file.getInputStream();
		List<ExcelRow> rows = ExcelUtil.getExcelRows(is, sourceType);
		customerDao.save(rows, sourceType);
	}

	@Override
	public List<CustomerDetail> getCustomerDetails(int offset, int limit) {
		List<CustomerDetail> customerDetails = customerDao.getCustomerDetails(offset, limit);
		return customerDetails;
	}

	@Override
	public List<CustomerDetail> getCustomerDetailsWithSearchAndPage(int offset, int limit,
			String searchStr) {
		List<CustomerDetail> customerDetails = customerDao.getCustomerDetailsWithSearchAndPage(offset, limit, searchStr);
		return customerDetails;
		
	}

	@Override
	public long getCustomerDetails(String searchStr) {
		return customerDao.getCustomerDetails(searchStr);
	}

	@Override
	public long getTotalRecords() {
		return customerDao.getTotalRecords();
	}

}
