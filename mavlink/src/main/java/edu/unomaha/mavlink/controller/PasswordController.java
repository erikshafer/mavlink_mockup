package edu.unomaha.mavlink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.unomaha.mavlink.domain.User;
import edu.unomaha.mavlink.repository.UserService;

@Controller
@RequestMapping("/password")
public class PasswordController {


	@Autowired
	private UserService userService;
	
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


	@RequestMapping(value = "/forgot/", method = RequestMethod.GET)
	public String forgotPasswordGet(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "forgotPassword";
	}

	@RequestMapping(value = "/reset/", method = RequestMethod.POST)
	public String resetPasswordGet(@ModelAttribute("user") User user, Model model) {

		// If the username and e-mail are found...
		if (userService.checkUserExists(user.getUsername(), user.getEmail())) {
			User existingUser = userService.findByUsername(user.getUsername()); // grab user
			
			String encryptedPassword = passwordEncoder.encode(user.getPassword()); // ENCRYPT
			existingUser.setPassword(encryptedPassword); // load new pass to existing user

			model.addAttribute("user", existingUser); // add to model
			userService.saveUser(existingUser); // save to repo

			return "redirect:/index"; // success
		} else {
			return "redirect:/index?error";
		}
	}

	// Deprecated
	/*
	 * @RequestMapping(value = "/save/", method = RequestMethod.POST) public
	 * String forgotPasswordGet(@ModelAttribute("user") User user, Model model)
	 * {
	 * 
	 * User existingUser = userService.findByUsername(user.getUsername()); //
	 * grab user existingUser.setPassword(user.getPassword()); // load new pass
	 * to existing user
	 * 
	 * model.addAttribute("user", existingUser); // add to model
	 * userService.saveUser(existingUser); // save to repo
	 * 
	 * return "redirect:/index"; // success
	 * 
	 * }
	 */

}
