package com.journaldev.spring.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.journaldev.spring.model.User;

@Controller
public class HomeController {

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		System.out.println("Home Page Requested, locale = " + locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String user(@Validated User user, Model model) {
//		System.out.println("User Page Requested");
//		model.addAttribute("userName", user.getUserName());
//		return "user";

		// step1
		/**
		 * 代码块一
		 */
		/**
		 * log一下预期的结果，结果正确，继续下一步
		 */

		// step2
		/**
		 * 代码块2
		 */
		/**
		 * log一下预期的结果，结果正确，继续下一步
		 */// step3

		/**
		 * 代码块3
		 */
		/**
		 * log一下预期的结果，结果正确，继续下一步
		 */



		return null;
	}
}
