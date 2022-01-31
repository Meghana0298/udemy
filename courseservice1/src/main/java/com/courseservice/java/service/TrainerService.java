package com.courseservice.java.service;

import javax.validation.Valid;

import com.courseservice.java.dto.CourseRequestDto;

public interface TrainerService {

	String addCourse(@Valid CourseRequestDto courseRequestDto,Integer userId);

	String editCourse(@Valid CourseRequestDto courseRequestDto, Integer courseId, Integer userId);

}
