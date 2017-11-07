package com.hkshah.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Hardik Shah
 *
 */
@Controller
public class SpringController {

	@RequestMapping(value = "/")
	public String homePage(Model model) {
		return "home";
	}
}