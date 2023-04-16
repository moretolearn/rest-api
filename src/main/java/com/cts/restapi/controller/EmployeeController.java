package com.cts.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.restapi.dto.EmployeeDto;
import com.cts.restapi.models.Employee;
import com.cts.restapi.models.Employee.EmployeeBuilder;
import com.cts.restapi.repository.EmployeeRepository;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/employee")
@CrossOrigin
@Validated
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@PostMapping("/add")
	public Employee addEmployee(@Valid @RequestBody Employee employee) {
		System.err.println(employee);
		return employeeRepository.save(employee);
	}

	@PreAuthorize("hasAuthority('USER')")
//	@RolesAllowed("ADMIN")
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> getEmployees() {
//		return employeeRepository.findAll();
		return employeeRepository.getCustomAllEmployees();
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
//	@RolesAllowed("USER")
	@GetMapping(value = "/{id}")
	public String getEmployeesById(@PathVariable("id") int empId) {
		return "hi "+empId;
	}
	
//	@GetMapping()
//	public String getEmployeesBName(@RequestParam("name") @NotEmpty String name) {
//		return "hi "+name;
//	}
}
