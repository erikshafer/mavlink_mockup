package edu.unomaha.mavlink.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/courses")
public class CourseController {

	@RequestMapping("/")
	public String viewCoursesAll(Model model) {
		return "redirect:/login/";
	}
	
}
