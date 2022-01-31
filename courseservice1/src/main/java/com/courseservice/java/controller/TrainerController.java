package com.courseservice.java.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.courseservice.java.dto.CourseRequestDto;
import com.courseservice.java.service.TrainerService;

@RestController
public class TrainerController {

	@Autowired
	TrainerService trainerService;

	@PostMapping("/courses")
	public ResponseEntity<String> addCourse(@Valid @RequestBody CourseRequestDto courseRequestDto,
			@RequestParam Integer userId) {
		String response = trainerService.addCourse(courseRequestDto, userId);
		return new ResponseEntity<String>(response, HttpStatus.CREATED);
	}

	// Api for updation of courses
	@PutMapping("/courses/editCourse")
	public ResponseEntity<String> editCourse(@Valid @RequestBody CourseRequestDto courseRequestDto,
			@RequestParam Integer courseId, @RequestParam Integer userId) {
		String response = trainerService.editCourse(courseRequestDto, courseId, userId);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
}
