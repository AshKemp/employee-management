package com.ashwin.javaguides.springbootbackend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashwin.javaguides.springbootbackend.exception.ResourceNotFoundException;
import com.ashwin.javaguides.springbootbackend.model.Employee;
import com.ashwin.javaguides.springbootbackend.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")
	public List<Employee> getEmployees(){
		return employeeRepository.findAll();
	}
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee Not found for id: "+id));
		return ResponseEntity.ok(employee);	
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employeeDetails) {
		Employee employee = employeeRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Employee Not found for id: "+id));
		
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmail(employeeDetails.getEmail());
		
		Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Employee Not found for id: "+id));
		
		employeeRepository.delete(employee);
		Map<String,Boolean> response = new HashMap();
		response.put("deleted", Boolean.TRUE);	
		return ResponseEntity.ok(response); 
	}
}
