package edu.unomaha.mavlink.controller;

import java.security.Principal;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.unomaha.mavlink.domain.User;
import edu.unomaha.mavlink.domain.security.UserRole;
import edu.unomaha.mavlink.repository.RoleDao;
import edu.unomaha.mavlink.repository.UserRepository;
import edu.unomaha.mavlink.repository.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleDao roleDao;

	@RequestMapping("/")
	public String home() {
		return "redirect:/index";
	}

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/login")
	public String loginRouteToUserFront() {
		return "redirect:/userFront";
	}

	@RequestMapping(value = "/signup/", method = RequestMethod.GET)
	public String signup(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "signup";
	}

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

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());

        model.addAttribute("user", user);

        return "profile";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String profilePost(@ModelAttribute("user") User newUser, Model model) {
        User user = userService.findByUsername(newUser.getUsername());
        user.setUsername(newUser.getUsername());
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setEmail(newUser.getEmail());
        user.setMajor(newUser.getMajor());

        model.addAttribute("user", user);

        userService.saveUser(user);

        return "profile";
    }

}
