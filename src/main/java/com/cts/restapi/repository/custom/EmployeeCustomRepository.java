package com.cts.restapi.repository.custom;

import java.util.List;

import com.cts.restapi.models.Employee;

public interface EmployeeCustomRepository {

	List<Employee> getCustomAllEmployees();
}
