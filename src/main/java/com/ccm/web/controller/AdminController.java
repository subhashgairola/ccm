package com.ccm.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	
	@RequestMapping("/customers")
	public @ResponseBody List<CustomerDetail> getCustomerDetails() {
		List<CustomerDetail> customerDetails = customerService.getCustomerDetails();
		return customerDetails;
	}
	
	@RequestMapping(value = "/admin/upload", method = RequestMethod.POST)
	public  String uploadFileHandler(@RequestParam("file") MultipartFile file, @RequestParam("sourceType") String sourceType,Model model) {
		if (!file.isEmpty()) {
			try {
				customerService.save(file, sourceType);
				model.addAttribute("success","You successfully uploaded file " + file.getOriginalFilename());
			} catch (Exception e) {
				e.printStackTrace();
				 model.addAttribute("error","Error while uploading file " + file.getOriginalFilename());
			}
		} else {
			 model.addAttribute("error", "You failed to upload " + file.getOriginalFilename()
					+ " because the file was empty.");
		}
		return "adminPage";
	}
}
