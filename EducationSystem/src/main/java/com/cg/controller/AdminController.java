package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entities.Admin;
import com.cg.entities.Student;
import com.cg.services.AdminService;
@CrossOrigin("*")
@RestController
@RequestMapping("/edu/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
//
//	@PostMapping("/login")
//	public ResponseEntity<Admin> adminLogin(@RequestBody Admin admin) {
//
//		String username = admin.getUserName();
//		String password = admin.getPassword();
//
//		Admin a = adminService.adminLogin(username, password);
//
//		return new ResponseEntity<Admin>(a, HttpStatus.OK);
//	}

	@PostMapping("/login")
	public ResponseEntity<Admin> doAdminLogin(@RequestParam("username") final String username,
			@RequestParam("password") final String password) {

		Admin admin= adminService.adminLogin(username, password);

		ResponseEntity<Admin> responseEntity = new ResponseEntity<>(admin, HttpStatus.OK);

		return responseEntity;

	}
}
//@PostMapping("/student-login")
//public ResponseEntity<Student> doStudentLogin(@RequestParam("username") final String username,
//		@RequestParam("password") final String password) {
//
//	Student student = studentService.studentLogin(username, password);
//
//	ResponseEntity<Student> responseEntity = new ResponseEntity<>(student, HttpStatus.OK);
//
//	return responseEntity;
//
//}