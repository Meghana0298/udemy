package com.courseservice.java.service;

import java.util.List;

import com.courseservice.java.dto.CourseResponseDto;
import com.courseservice.java.entity.Course;
import com.courseservice.java.exception.CourseNotFoundException;

public interface LearnerService {

	List<CourseResponseDto> viewCourses();

	CourseResponseDto searchCourseByCourseName(String courseName) throws CourseNotFoundException;

	CourseResponseDto viewCourseByCourseId(Integer courseId) throws CourseNotFoundException;

}
