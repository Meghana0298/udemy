package com.courseservice.java.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.courseservice.java.entity.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {

	Optional<Course> findByCourseNameContaining(String courseName);

}
