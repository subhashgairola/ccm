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
	public List<ExcelRow> save(MultipartFile file, String sourceType) throws IOException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, InvalidExcelException, DataAccessException {
		InputStream is = file.getInputStream();
		List<ExcelRow> rows = ExcelUtil.getExcelRows(is, sourceType);
		List<ExcelRow> errorRows = customerDao.saveRows(rows, sourceType);
		return errorRows;
	}

	@Override
	public List<CustomerDetail> getCustomerDetails(int offset, int limit, String orderByCol, String sortOrder) throws DataAccessException{
		List<CustomerDetail> customerDetails = customerDao.getCustomerDetails(offset, limit, orderByCol, sortOrder);
		return customerDetails;
	}

	@Override
	public List<CustomerDetail> getCustomerDetailsWithSearchAndPage(int offset, int limit,
			String searchStr, String orderByCol, String sortOrder) throws DataAccessException{
		List<CustomerDetail> customerDetails = customerDao.getCustomerDetailsWithSearchAndPage(offset, limit, searchStr, orderByCol, sortOrder);
		return customerDetails;
		
	}

	@Override
	public long getCountWithSearch(String searchStr) throws DataAccessException{
		return customerDao.getCountBySearch(searchStr);
	}

	@Override
	public long getTotalRecords() throws DataAccessException{
		return customerDao.getTotalRecords();
	}

	@Override
	public void save(CustomerDetail customerDetail) throws DataAccessException{
		customerDao.save(customerDetail);		
	}

	@Override
	public boolean isCustomerDuplicate(String paramType, String param, int cusDetailId) {
		return customerDao.isCustomerDuplicate(paramType, param, cusDetailId);
	}

}
