package com.sp.controller;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sp.dto.GenericResponse;
import com.sp.entity.Employee;
import com.sp.exception.EmployeeNotFoundException;
import com.sp.repository.EmployeeRepository;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository repository;
	
	@Autowired
	private Environment env;
	
	@PostMapping("/employees")
	public ResponseEntity<GenericResponse> saveEmployee(@RequestBody Employee employee) {
		Employee empObj = repository.save(employee);
		empObj.setEnvironment(env.getProperty("server.port"));
		URI empUri = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{empId}")
				.buildAndExpand(empObj.getEmpId()).toUri();
		return ResponseEntity.created(empUri)
				.body(new GenericResponse(HttpStatus.CREATED.value(), "employee added successfully", empObj, null));
	}
	
	@PutMapping("/employees")
	public ResponseEntity<GenericResponse> updateEmployee(@RequestBody Employee employee) {
		Employee empObj = repository.save(employee);
		return ResponseEntity.ok(new GenericResponse(HttpStatus.OK.value(), "employee updated successfully", empObj, null));
	}
	
	@DeleteMapping("/employees/{empId}")
	public ResponseEntity<GenericResponse> deleteEmployee(@PathVariable String empId) {
		Employee empObj = repository.findById(empId)
				.orElseThrow(() -> new EmployeeNotFoundException("employee not found!"));
		repository.deleteById(empId);
		return ResponseEntity.ok(new GenericResponse(HttpStatus.OK.value(), "employee deleted successfully", empObj, null));
	}
	
	@GetMapping("/employees/{empId}")
	public ResponseEntity<GenericResponse> getEmployee(@PathVariable String empId) {
		Employee empObj = repository.findById(empId)
				.orElseThrow(() -> new EmployeeNotFoundException("employee not found!"));
		return ResponseEntity.ok(new GenericResponse(HttpStatus.OK.value(), null, empObj, null));
	}
	
	@GetMapping("/employees")
	public ResponseEntity<GenericResponse> getEmployees() {
		List<Employee> empList = repository.findAll();
		empList.forEach(emp -> emp.setEnvironment(env.getProperty("server.port")));
		return ResponseEntity.ok(new GenericResponse(HttpStatus.OK.value(), null, null, empList));
	}

}
