package com.javaguides.springboot.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.javaguides.springboot.entity.Employee;
import com.javaguides.springboot.exception.ResourceNotFoundException;
import com.javaguides.springboot.repository.EmployeeRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000/")
public class EmployeeController {

	@Autowired private EmployeeRepository repository;
	
	@GetMapping("/employees")
	public List<Employee> fetchEmployees(){
		return repository.findAll();
	}
	@PostMapping
	public Employee saveEmployee(@RequestBody Employee employee) {
		return repository.save(employee);
	}
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmpById(@PathVariable long id){
		Employee employee =  repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("employee does not existed"));
		return ResponseEntity.ok(employee);
	}
	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee empdetails){
		Employee updatedEmployee = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("employee does not exist with id"+id));
		updatedEmployee.setFirstName(empdetails.getFirstName());
		updatedEmployee.setSecondName(empdetails.getSecondName());
		updatedEmployee.setEmail(empdetails.getEmail());
		
		repository.save(updatedEmployee);
		
		return ResponseEntity.ok(updatedEmployee);
	}
	@DeleteMapping("{id}")
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){
		Employee employee = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("employee does not exist with id"+id));
		repository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
