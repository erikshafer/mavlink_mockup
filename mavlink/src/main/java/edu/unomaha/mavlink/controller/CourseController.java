package edu.unomaha.mavlink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.unomaha.mavlink.repository.CourseRepository;

@Controller
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseRepository courseRepository;

	// View all of the courses
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String viewAllCourses(Model model) {
		model.addAttribute("courses", courseRepository.findAll());
		return "courses";
	}
	
	// View all of the active courses
	@RequestMapping(value = "/active/", method = RequestMethod.GET)
	public String viewAllActiveCourses(Model model) {
		model.addAttribute("courses", courseRepository.findByActive(true));
		return "courses";
	}
	
	// View all of the active courses
	@RequestMapping(value = "/CSCI/", method = RequestMethod.GET)
	public String viewAllCSCICourses(Model model) {
		model.addAttribute("courses", courseRepository.findByCodeStartingWith("CSCI"));
		return "courses";
	}
	
	// View all of the active courses
	@RequestMapping(value = "/CIST/", method = RequestMethod.GET)
	public String viewAllCISTCourses(Model model) {
		model.addAttribute("courses", courseRepository.findByCodeStartingWith("CIST"));
		return "courses";
	}

	// View all of the courses of a certain credit amount
	@RequestMapping(value = "/credit/{credit}", method = RequestMethod.GET)
	public String viewAllCoursesOfCreditNum(@PathVariable Integer credit, Model model) {
		model.addAttribute("courses", courseRepository.findByCredits(credit));
		return "courses";
	}
	
	// View one specific course
	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	public String viewAllCoursesOfCreditNum(@PathVariable Long id, Model model) {
		if (courseRepository.findById(id) == null) {
			return "redirect:/courses/";
		}
		model.addAttribute("course", courseRepository.findById(id));
		return "course";
	}
	
}
