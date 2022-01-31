package com.courseservice.java.serviceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courseservice.java.dto.CourseResponseDto;
import com.courseservice.java.entity.Course;
import com.courseservice.java.exception.CourseNotFoundException;
import com.courseservice.java.repo.CourseRepository;
import com.courseservice.java.service.LearnerService;

@Service
public class LearnerServiceImpl implements LearnerService {

	@Autowired
	CourseRepository courseRepository;

	@Override
	public List<CourseResponseDto> viewCourses() {
		List<CourseResponseDto> CourseResponseDtoList = new ArrayList<CourseResponseDto>();
		Iterator<Course> it = courseRepository.findAll().iterator();
		while (it.hasNext()) {
			CourseResponseDto courseResponseDto = new CourseResponseDto();
			BeanUtils.copyProperties(it.next(), courseResponseDto);
			CourseResponseDtoList.add(courseResponseDto);

		}
		return CourseResponseDtoList;
	}

	@Override
	public CourseResponseDto searchCourseByCourseName(String courseName) throws CourseNotFoundException {
		Course course = new Course();
		CourseResponseDto courseResponseDto = new CourseResponseDto();
		Optional<Course> optionalCourse = courseRepository.findByCourseNameContaining(courseName);
		if (optionalCourse.isPresent()) {
			course = optionalCourse.get();
			BeanUtils.copyProperties(course, courseResponseDto);
			return courseResponseDto;

		} else {
			throw new CourseNotFoundException("course not found with the given name:" + courseName);

		}
	}

	@Override
	public CourseResponseDto viewCourseByCourseId(Integer courseId) throws CourseNotFoundException {
		Optional<Course> opCourse = courseRepository.findById(courseId);
		CourseResponseDto courseResponseDto = new CourseResponseDto();
		Course course = new Course();
		if (opCourse.isPresent()) {
			course = opCourse.get();
			BeanUtils.copyProperties(course, courseResponseDto);
			return courseResponseDto;
		} else {
			throw new CourseNotFoundException("course not found with the given id:" + courseId);

		}

	}

}
