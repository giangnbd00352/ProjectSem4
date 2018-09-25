package com.ensat.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ensat.entities.ImageOrder;
import com.ensat.entities.User;
import com.ensat.services.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	// @RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	// public ModelAndView login() {
	// ModelAndView model = new ModelAndView();
	//
	// model.setViewName("user/login");
	// return model;
	// }

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

//	@RequestMapping(value = { "/signup" }, method = RequestMethod.GET)
//	public ModelAndView signup() {
//		ModelAndView model = new ModelAndView();
//		User user = new User();
//		model.addObject("user", user);
//		model.setViewName("user/signup");
//
//		return model;
//	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		
		return "signup";
	}

	@RequestMapping(value = { "/signup" }, method = RequestMethod.POST)
	public String createUser(@Valid User user, BindingResult bindingResult, Model model) {
		User userExists = userService.findUserByEmail(user.getEmail());

		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user", "This email already exists!");
		}
		if (bindingResult.hasErrors()) {
			return "signup";
		} else {
			userService.saveUser(user);
			model.addAttribute("msg", "User has been registered successfully!");
			model.addAttribute("user", new User());
		}

		return "login";
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());

		model.addAttribute("userName", user.getFirstname() + " " + user.getLastname());
		
		ImageOrder order = new ImageOrder();
		model.addAttribute("order", order);
		
		return "home";
	}
	
	@RequestMapping(value = { "/profile" }, method = RequestMethod.GET)
	public String showProfile(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		model.addAttribute("user", userService.findUserByEmail(auth.getName()));
		return "profile";
	}
	
	@RequestMapping(value = { "/profile" }, method = RequestMethod.POST)
	public String editProfile(User user) {
		userService.saveUser(user);
		return "profile";
	}

	@RequestMapping(value = { "/access_denied" }, method = RequestMethod.GET)
	public String accessDenied(Model model) {
		return "access_denied";
	}
}
