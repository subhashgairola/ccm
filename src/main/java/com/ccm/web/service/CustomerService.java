package com.ccm.web.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ccm.excel.utils.CustomerDetail;

public interface CustomerService {

	void save(MultipartFile file, String sourceType) throws IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException;
	List<CustomerDetail> getCustomerDetails();
}
