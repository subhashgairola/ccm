package com.ccm.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.lang.model.element.Element;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ccm.datatable.utils.DataTablesRequest;
import com.ccm.datatable.utils.DataTablesResponse;
import com.ccm.datatable.utils.Search;
import com.ccm.excel.utils.CustomerDetail;
import com.ccm.excel.utils.InvalidExcelException;
import com.ccm.web.entity.State;
import com.ccm.web.service.CustomerService;
import com.ccm.web.service.StateService;

@Controller
public class AdminController {

	@Resource
	private CustomerService customerService;

	@Resource
	private StateService stateService;

	private static final Log LOGGER = LogFactory.getLog(AdminController.class);

	@RequestMapping("/user/userPage")
	public String getUserPage(ModelMap model) {
		model.addAttribute("msg", "Welcome page for the Normal users!!");
		return "userPage";
	}

	@RequestMapping("/admin/adminPage")
	public String getAdminPage(ModelMap model) {
		model.addAttribute("msg", "Welcome page for the Admin User!!");
		return "adminPage";
	}

	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	@ResponseBody
	public CustomerDetail updateCustomer(@RequestBody(required = false) CustomerDetail customerDetail) {
		LOGGER.info("********************** updateCustomer() start: Customer Detail " + customerDetail);
		try {
			customerService.save(customerDetail);
		} catch (DataAccessException e) {
			LOGGER.error("DB exeption occurred while updating customer. ", e);
		} catch (Exception e) {
			LOGGER.error("Exeption occurred while updating customer. ", e);
		}
		LOGGER.info("********************** updateCustomer() ********************** End");
		return customerDetail;
	}

	@RequestMapping(value = "/customers", method = RequestMethod.POST)
	@ResponseBody
	public DataTablesResponse<Element> getCustomers(@RequestBody(required = false) DataTablesRequest dataTablesRequest) {
		LOGGER.info("********************** getCustomers()********************** start. Request is " + dataTablesRequest);
		List<CustomerDetail> customerDetails = null;
		long totalFilteredRecords = 0;
		long totalRecords = 0;
		int offset = dataTablesRequest.getStart();
		int limit = dataTablesRequest.getLength();
		Search search = dataTablesRequest.getSearch();
		String searchStr = search.getValue();
		try {
			totalRecords = customerService.getTotalRecords();
			if (searchStr.equals("")) {
				customerDetails = customerService.getCustomerDetails(offset, limit);
				totalFilteredRecords = customerService.getTotalRecords();
			} else {
				totalFilteredRecords = customerService.getCustomerDetails(searchStr);
				customerDetails = customerService.getCustomerDetailsWithSearchAndPage(offset, limit, searchStr);
			}
		} catch (DataAccessException e) {
			LOGGER.error("DB exception in getCustomers() ", e);
			return new DataTablesResponse<Element>(dataTablesRequest.getDraw(), totalRecords, totalFilteredRecords,
					"Error fetching customer records.", customerDetails);
		} catch (Exception e) {
			LOGGER.error("Exception in getCustomers() ", e);
			return new DataTablesResponse<Element>(dataTablesRequest.getDraw(), totalRecords, totalFilteredRecords,
					"Error fetching customer records.", customerDetails);
		}
		LOGGER.info("getCustomers ended:");
		return new DataTablesResponse<Element>(dataTablesRequest.getDraw(), totalRecords, totalFilteredRecords, "", customerDetails);

	}

	@RequestMapping(value = "/admin/upload", method = RequestMethod.POST)
	public void uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("sourceType") String sourceType, HttpServletResponse response)
			throws IOException {
		LOGGER.info("********************** uploadFile()**********************started. File is " + file.getOriginalFilename());
		String msg = "";
		String fileName = "";
		try {
			fileName = file.getOriginalFilename();
			String fileFormat = fileName.substring(fileName.lastIndexOf('.'), fileName.length());
			if (fileFormat.equalsIgnoreCase(".xls") || fileFormat.equalsIgnoreCase(".xlsx")) {
				customerService.save(file, sourceType);
				msg = "File: " + fileName + " uploaded successfully.";
			} else {
				msg = "Error uploading " + fileName + " due to incorrect file type. The allowed formats are .xls and .xlsx.";
				LOGGER.error("Invalid excel format: " + msg);
			}
		} catch (InvalidExcelException ex) {
			msg = ex.getMessage();
			LOGGER.error("Invalid excel format: " + msg);
		} catch (DataAccessException e) {
			msg = "Error uploading file: " + fileName;
			LOGGER.error("DB exception while saving file ", e);
		} catch (Exception e) {
			msg = "Error uploading file: " + fileName;
			LOGGER.error(msg, e);
		}

		PrintWriter responseWriter = response.getWriter();
		responseWriter.write(msg);
		responseWriter.flush();
		responseWriter.close();
		LOGGER.info("********************** uploadFile()**********************ended.");
	}

	@ResponseBody
	@RequestMapping("/states")
	public List<State> getStates() {
		LOGGER.info("**********************getStates()**********************started.");
		List<State> states = null;
		try {
			states = stateService.getAllStates();
		} catch (DataAccessException e) {
			LOGGER.error("DB exception while fetching states ", e);
		} catch (Exception e) {
			LOGGER.error("Exception while fetching states ", e);
		}
		LOGGER.info("**********************getStates()**********************ended.");
		return states;
	}
}
