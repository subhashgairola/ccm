package com.ccm.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.lang.model.element.Element;
import javax.servlet.http.HttpServletResponse;

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
import com.ccm.web.service.CustomerService;

@Controller
public class AdminController {

	@Resource
	private CustomerService customerService;

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
	public CustomerDetail updateCustomer(
			@RequestBody(required = false) CustomerDetail customerDetail) {
		customerService.save(customerDetail);
		return customerDetail;

	}

	@RequestMapping(value = "/customers", method = RequestMethod.POST)
	@ResponseBody
	public DataTablesResponse<Element> list(
			@RequestBody(required = false) DataTablesRequest dataTablesRequest) {
		List<CustomerDetail> customerDetails = null;
		long totalFilteredRecords = 0;
		long totalRecords = 0;
		int offset = dataTablesRequest.getStart();
		int limit = dataTablesRequest.getLength();
		Search search = dataTablesRequest.getSearch();
		String searchStr = search.getValue();
		totalRecords = customerService.getTotalRecords();

		if (searchStr.equals("")) {
			customerDetails = customerService.getCustomerDetails(offset, limit);
			totalFilteredRecords = customerService.getTotalRecords();
		} else {
			totalFilteredRecords = customerService
					.getCustomerDetails(searchStr);
			customerDetails = customerService
					.getCustomerDetailsWithSearchAndPage(offset, limit,
							searchStr);
		}

		return new DataTablesResponse<Element>(dataTablesRequest.getDraw(),
				totalRecords, totalFilteredRecords, "", customerDetails);

	}

	@RequestMapping(value = "/admin/upload", method = RequestMethod.POST)
	public void uploadFileHandler(@RequestParam("file") MultipartFile file,
			@RequestParam("sourceType") String sourceType,
			HttpServletResponse response) {
		String msg = "";
		try {
			customerService.save(file, sourceType);
			msg = "File: " + file.getOriginalFilename() + " uploaded successfully";
		} catch (Exception e) {
			msg = "Error while uploading file: "
					+ file.getOriginalFilename();
		}

		try {
			PrintWriter responseWriter = response.getWriter();
			responseWriter.write(msg);
			responseWriter.flush();
			responseWriter.close();
		} catch (IOException e) {

		}
		return;
	}
}
