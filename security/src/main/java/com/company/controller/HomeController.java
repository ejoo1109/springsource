package com.company.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	
	/*
	 * 접근 제한org.springframework.security.authentication.
	 * UsernamePasswordAuthenticationToken@8fc7dd: Principal: 
	 * org.springframework.security.core.userdetails.User@bfc28a9a: 
	 * Username: member; Password: [PROTECTED]; Enabled: true; 
	 * AccountNonExpired: true; credentialsNonExpired: 
	 * true; AccountNonLocked: true; Granted Authorities: 
	 * ROLE_MEMBER; Credentials: [PROTECTED]; Authenticated: true;
	 * Details: org.springframework.security.web.authentication.
	 * WebAuthenticationDetails@0: RemoteIpAddress: 0:0:0:0:0:0:0:1; 
	 * SessionId: 3F8968C448D057DB54A3177F96777C92; Granted Authorities: 
	 * ROLE_MEMBER
	 * 
	 * 
	 * */
	@GetMapping("/accessError")
	public String accessError(Authentication auth) {
		logger.info("접근 제한"+auth);
		return "/security/accessError";
	}
}
