package com.ccm.web.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	private final Log logger = LogFactory.getLog(LoginController.class);

	@RequestMapping(value = "/login/loginPage", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "loginPage";

	}

	/**
	 * This method called when spring security return true
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/login/loginPass", method = RequestMethod.GET)
	public String loginPass(Model map, HttpSession session, HttpServletRequest req) {
		logger.info("********************** loginPass() ****************************Start");
		String userName = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		
		Collection<? extends GrantedAuthority> roleNames = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		String roleName = "";
		for (GrantedAuthority role : roleNames) {
			roleName = role.getAuthority();
			logger.info("********************** user.roleName() " + roleName);
			break;
		}
		
		String retstr = "";
		
		if("ROLE_ADMIN".equalsIgnoreCase(roleName)){
			retstr="redirect:../admin/adminPage";
		}else if("ROLE_USER".equalsIgnoreCase(roleName)){
			retstr="redirect:../user/userPage";
		}
		
		logger.info("********************** Logged In successfully by user  --  "+userName);
		
		return retstr;
	}

	@RequestMapping(value = "/login/loginFailed", method = RequestMethod.GET)
	public String loginFailed(Model map, HttpSession session) {

		logger.info("********************** loginFailed() ****************************END");
		return "loginPage";

	}

	
	@RequestMapping(value = "/login/logout")
	public String logout(Model map, HttpServletRequest req) {

		 logger.info("*******************************session invalidated");
		 req.getSession().invalidate();
		 SecurityContextHolder.clearContext();
		return "redirect:loginPage";
	}
	
	
}