package com.ccm.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public  String uploadFileHandler(@RequestParam("file") MultipartFile file, @RequestParam("sourceType") String sourceType,Model model) {
		if (!file.isEmpty()) {
			try {
				excelService.save(file, sourceType);
				model.addAttribute("success","You successfully uploaded file " + file.getOriginalFilename());
			} catch (Exception e) {
				 model.addAttribute("error","Error while uploading file " + file.getOriginalFilename());
			}
		} else {
			 model.addAttribute("error", "You failed to upload " + file.getOriginalFilename()
					+ " because the file was empty.");
		}
		return "adminPage";
	}
}
