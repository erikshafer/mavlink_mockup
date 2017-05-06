package edu.unomaha.mavlink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.unomaha.mavlink.repository.CourseRepository;
import edu.unomaha.mavlink.repository.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private UserService userService;

	// View all of the courses
	@RequestMapping(value = "/")
	public String viewAllCourses() {
		return "redirect:/";
	}
	
	// View all of the active courses
	@RequestMapping(value = "/users/", method = RequestMethod.GET)
	public String viewAllActiveCourses(Model model) {
		model.addAttribute("users", userService.findUserList());	// get all users
		return "users";
	}
	
	// View one specific user
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public String viewAllCoursesOfCreditNum(@PathVariable Long id, Model model) {
		if (courseRepository.findById(id) == null) {
			return "redirect:/admin/users/";
		}
		model.addAttribute("user", userService.findById(id));	// get one user
		return "profile"; // use the same profile template
	}
	
}
