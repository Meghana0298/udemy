package com.courseservice.java.serviceImpl;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courseservice.java.dto.CourseRequestDto;
import com.courseservice.java.entity.Course;
import com.courseservice.java.feign.UserServiceClient;
import com.courseservice.java.repo.CourseRepository;
import com.courseservice.java.service.TrainerService;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

@Service
public class TrainerServiceImpl implements TrainerService {

	@Autowired
	CourseRepository courseRepository;
	@Autowired
	UserServiceClient userServiceClient;
	@Autowired
	CircuitBreakerRegistry circuitBreakerRegistry;

	@Override
	public String addCourse(@Valid CourseRequestDto courseRequestDto, Integer userId) {
		
		try {
		         if (userServiceClient.getRoleByUserId(userId).equalsIgnoreCase("ROLE_TRAINER")) {
			         Course course = new Course();
			         BeanUtils.copyProperties(courseRequestDto, course);
			         Course createdCourse = courseRepository.save(course);
			         if (createdCourse != null)
				          return " Course created successfully";
			          return "Course creation was unsuccessfull";
		          } else
			    return "Create operation is not authorized for role=" + userServiceClient.getRoleByUserId(userId);
		}
		catch(Exception e ) {
			e.getMessage();
		} finally {
			CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("usercircuitbreaker");
			System.out.println("Successful call count: "+ circuitBreaker.getMetrics().getNumberOfSuccessfulCalls()
					+ " | Failed call count: " + circuitBreaker.getMetrics().getNumberOfFailedCalls()
					+ " | Failure rate %:" + circuitBreaker.getMetrics().getFailureRate()
					+ " | Failure threshold size :" + circuitBreaker.getCircuitBreakerConfig().getFailureRateThreshold()
					+ " | window size :" + circuitBreaker.getCircuitBreakerConfig().getFailureRateThreshold() +
					" | state: " + circuitBreaker.getState());
		}
		return null;
	}

	@Override
	public String editCourse(@Valid CourseRequestDto courseRequestDto, Integer courseId, Integer userId) {
		if (userServiceClient.getRoleByUserId(userId).equalsIgnoreCase("ROLE_TRAINER")) {
			Course course = courseRepository.findById(courseId).get();
			course.setUserId(course.getUserId());
			course.setCourseName(courseRequestDto.getCourseName());
			course.setPrice(courseRequestDto.getPrice());
			Course updatedCourse = courseRepository.save(course);
			if (updatedCourse != null)
				return " Course updated successfully";
			return "Course updation was unsuccessfull";
		} else
			return "Update operation is not authorized for role=" + userServiceClient.getRoleByUserId(userId);
	}

}
