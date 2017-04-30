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
	@Column(name = "COURSE_ID", nullable = false, updatable = false)
	private Long id;

	@Column(name = "COURSE_NAME")
	private String name;

	@Column(name = "COURSE_CODE")
	private String code;

	@Column(name = "COURSE_DESCRIPTION")
	private String description;

	@Column(name = "COURSE_CREDITS")
	private Integer credits;

	@Column(name = "COURSE_ACTIVE")
	private Boolean active;

	public Course() {
		super();
	}

	public Course(Long id, String name, String code, String description, Integer credits, Boolean active) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.description = description;
		this.credits = credits;
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

	public Integer getCredits() {
		return credits;
	}

	public void setCredits(Integer credits) {
		this.credits = credits;
	}

}
