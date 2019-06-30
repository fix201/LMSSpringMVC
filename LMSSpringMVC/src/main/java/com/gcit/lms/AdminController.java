package com.gcit.lms;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gcit.lms.service.AdminService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "lmsadmin";
	}
	
}
