package com.springboot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

@RestController
@CrossOrigin
@RequestMapping("/api/v1/")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepo empRepo;
	
	
//	Get all Employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return empRepo.findAll();
	}
//	create employee rest api 
	@PostMapping(value = "/employees",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public Employee createEmployee(@RequestBody Employee employee) {
		return empRepo.save(employee);
	}
	
//	get employee rest api
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = empRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Employee " +id + "not found"));
		return ResponseEntity.ok(employee);
	}

//	update employee rest api
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployeeById(@PathVariable Long id , @RequestBody Employee employeeDetails){
		Employee employee = empRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Employee " +id + "not found"));
		
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmail(employeeDetails.getEmail());
		
		Employee updatedEmployee = empRepo.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
//	delete employee
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Employee employee = empRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Employee " +id + "not found"));
		
		empRepo.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
