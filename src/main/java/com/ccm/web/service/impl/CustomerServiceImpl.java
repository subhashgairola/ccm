package com.ccm.web.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ccm.excel.utils.CustomerDetail;
import com.ccm.excel.utils.ExcelRow;
import com.ccm.excel.utils.ExcelUtil;
import com.ccm.excel.utils.InvalidExcelException;
import com.ccm.web.dao.CustomerDao;
import com.ccm.web.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Resource
	private CustomerDao customerDao;

	@Override
	public void save(MultipartFile file, String sourceType) throws IOException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, InvalidExcelException, DataAccessException {
		InputStream is = file.getInputStream();
		List<ExcelRow> rows = ExcelUtil.getExcelRows(is, sourceType);
		customerDao.save(rows, sourceType);
	}

	@Override
	public List<CustomerDetail> getCustomerDetails(int offset, int limit) throws DataAccessException{
		List<CustomerDetail> customerDetails = customerDao.getCustomerDetails(offset, limit);
		return customerDetails;
	}

	@Override
	public List<CustomerDetail> getCustomerDetailsWithSearchAndPage(int offset, int limit,
			String searchStr) throws DataAccessException{
		List<CustomerDetail> customerDetails = customerDao.getCustomerDetailsWithSearchAndPage(offset, limit, searchStr);
		return customerDetails;
		
	}

	@Override
	public long getCustomerDetails(String searchStr) throws DataAccessException{
		return customerDao.getCustomerDetails(searchStr);
	}

	@Override
	public long getTotalRecords() throws DataAccessException{
		return customerDao.getTotalRecords();
	}

	@Override
	public void save(CustomerDetail customerDetail) throws DataAccessException{
		customerDao.save(customerDetail);		
	}

}
