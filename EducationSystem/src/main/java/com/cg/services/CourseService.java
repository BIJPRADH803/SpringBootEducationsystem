package com.cg.services;

import java.util.List;

import com.cg.entities.Course;

public interface CourseService {

	public Course saveCourse(Course course);

	public Course getCourseById(int courseId);

	public List<Course> getAllCourses();

	public Course updateCourse(Course course);

	public void deleteCourseById(int courseId);
}
