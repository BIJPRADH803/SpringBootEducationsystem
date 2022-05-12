package com.cg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.entities.Course;
import com.cg.exception.ResourceNotFoundException;
import com.cg.repositories.CourseRepository;

@Service
public class CourseServiceimpl implements CourseService {

	@Autowired
	private CourseRepository courseRepo;

	@Override
	public Course saveCourse(Course course) {
		Course savedCourse = courseRepo.save(course);
		return savedCourse;
	}

	@Override
	public Course getCourseById(int courseId) {
		Optional<Course> optionalCourse = courseRepo.findById(courseId);
		if (optionalCourse == null)
			throw new ResourceNotFoundException("Course Not found with id : " + courseId);
		Course course = optionalCourse.get();
		return course;
	}

	@Override
	public List<Course> getAllCourses() {
		List<Course> courses = courseRepo.findAll();
		return courses;
	}

	@Override
	public Course updateCourse(Course course) {
		Course updatedCourse = getCourseById(course.getCourseId());
		updatedCourse = courseRepo.save(course);
		return updatedCourse;
	}

	@Override
	public void deleteCourseById(int courseId) {
		Course course = getCourseById(courseId);
		courseRepo.delete(course);
	}

}
