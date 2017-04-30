package edu.unomaha.mavlink.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.unomaha.mavlink.domain.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {
	Course findById(Long id);
	Course findByCode(String code);
	List<Course> findByName(String name);
	List<Course> findByCredits(Integer credits);
	List<Course> findByActive(Boolean bool);
}
