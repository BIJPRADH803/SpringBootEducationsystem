package com.cg.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entities.Student;
import com.cg.payload.StudentDTO;
import com.cg.services.StudentService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@PostMapping("/student-login")
	public ResponseEntity<Student> doStudentLogin(@RequestParam("username") String username,
			@RequestParam("password") String password) {

        Student student = studentService.studentLogin(username, password);
		
	
		ResponseEntity<Student> responseEntity = new ResponseEntity<>(student, HttpStatus.OK);

		return responseEntity;

	}
	
	@PostMapping("/request-registration")
	public ResponseEntity<StudentDTO> makeRegistration(@Valid @RequestBody Student student) {
	final boolean isRequestMade = studentService.requestRegistration(student);



	if (isRequestMade) {
	StudentDTO studentDto = new StudentDTO();
	studentDto.setFirstName(student.getFirstName());
	studentDto.setLastName(student.getLastName());
	studentDto.setContactNumber(student.getContactNumber());
	studentDto.setUserName(student.getUserName());
	studentDto.setEmailId(student.getEmailId());
	return new ResponseEntity<StudentDTO>(studentDto, HttpStatus.OK);



	} else {
	return new ResponseEntity("Registration request cannot be made", HttpStatus.OK);



	}



	}

	@GetMapping("/all")
	public List<Student> fetchAllStudents() {

		List<Student> students = studentService.getAllStudents();
		return students;
	}

	@PostMapping("/save")
	public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student) {

		Student newStudent = studentService.saveStudent(student);
		ResponseEntity<Student> responseEntity = new ResponseEntity<>(newStudent, HttpStatus.CREATED);
		return responseEntity;
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Object> fetchStudentById(@PathVariable("id") int studentId) {

		ResponseEntity<Object> responseEntity = null;
		Student student = studentService.getStudentById(studentId);
		responseEntity = new ResponseEntity<>(student, HttpStatus.OK);
		return responseEntity;
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteStudentById(@PathVariable("id") int studentId) {

		ResponseEntity<String> responseEntity = null;
		studentService.deleteStudent(studentId);
		responseEntity = new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
		return responseEntity;
	}

	@PutMapping("/update")
	public ResponseEntity<Object> updateStudent(@RequestBody Student student) {

		ResponseEntity<Object> responseEntity = null;
		Student updatedStudent = studentService.updateStudent(student);
		responseEntity = new ResponseEntity<>(updatedStudent, HttpStatus.OK);
		return responseEntity;
	}

}
