package com.cts.restapi.repository.custom;

import java.util.List;

import com.cts.restapi.models.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class EmployeeCustomRepositoryImpl implements EmployeeCustomRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<Employee> getCustomAllEmployees() {
		List<Employee> list = entityManager.createQuery("select e from Employee e",Employee.class).getResultList();
		return list;
	}

}
