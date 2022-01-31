package com.courseservice.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.courseservice.java.dto.CourseResponseDto;
import com.courseservice.java.entity.Course;
import com.courseservice.java.exception.CourseNotFoundException;
import com.courseservice.java.service.LearnerService;

@RestController
public class LearnerController {

	@Autowired
	LearnerService learnerService;

	// Api for getting list of All courses
	@GetMapping("/courses")
	public ResponseEntity<List<CourseResponseDto>> viewCourses() {
		return new ResponseEntity<List<CourseResponseDto>>(learnerService.viewCourses(), HttpStatus.OK);
	}

	// Api for getting course by passing courseName
	@GetMapping("/courses/{courseName}")
	public ResponseEntity<CourseResponseDto> searchCourseByCourseName(@PathVariable String courseName)
			throws CourseNotFoundException {
		return new ResponseEntity<CourseResponseDto>(learnerService.searchCourseByCourseName(courseName),
				HttpStatus.OK);
	}

	// Api for getting course by passing courseId
	@GetMapping("/course")
	public CourseResponseDto viewCourseByCourseId(@RequestParam Integer courseId) throws CourseNotFoundException {
		return learnerService.viewCourseByCourseId(courseId);
	}
}
