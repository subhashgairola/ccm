package com.ccm.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ccm.web.service.ExcelService;

@Controller
public class AdminController {
	
	@Resource
	private ExcelService excelService;

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
	
	@RequestMapping(value = "/admin/upload", method = RequestMethod.POST)
	public @ResponseBody String uploadFileHandler(@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				excelService.save(file);
				return "You successfully uploaded file=" + file.getName();
			} catch (Exception e) {
				return "You failed to upload " + file.getName() + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + file.getName()
					+ " because the file was empty.";
		}
	}
}
