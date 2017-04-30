package edu.unomaha.mavlink.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// WIP

@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "course_id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "course_name")
	private String name;

	@Column(name = "course_code")
	private String code;

	@Column(name = "course_description")
	private String description;

	@Column(name = "course_active")
	private Boolean active;

	public Course() {
		super();
	}

	public Course(Long id, String name, String code, String description, Boolean active) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.description = description;
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
