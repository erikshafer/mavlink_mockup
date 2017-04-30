package edu.unomaha.mavlink.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.unomaha.mavlink.domain.User;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String home() {
		return "redirect:/login/";
	}
	
	@RequestMapping("/login/")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "signup";
	}

	/*
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signupPost(@ModelAttribute("user") User user, Model model) {

		if (userService.checkUserExists(user.getUsername(), user.getEmail())) {
			if (userService.checkEmailExists(user.getEmail())) {
				model.addAttribute("emailExists", true);
			}
			if (userService.checkUsernameExists(user.getUsername())) {
				model.addAttribute("usernameExists", true);
			}
			return "signup";
		} else {
			Set<UserRole> userRoles = new HashSet<>();
			// Standard naming convention for Spring Security. ROLE, underscore,
			// ROLE NAME in all caps.
			// This is important as it'll be the basis for user authentication.
			// "ROLE_USER" needs to be added to the `role` table otherwise
			// registration will not work.
			// 04-04-2017: I've added code to schema.sql and data.sql that will
			// take care of this.
			userRoles.add(new UserRole(user, roleDao.findByName("ROLE_USER")));
			userService.createUser(user, userRoles);
			return "redirect:/";
		}
	}
	*/
	
}
