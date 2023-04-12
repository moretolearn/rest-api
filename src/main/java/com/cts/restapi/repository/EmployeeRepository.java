package com.cts.restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.restapi.dto.EmployeeDto;
import com.cts.restapi.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

//	@Query("select s.empName as name,s.empSal as salary from Employee s")
//	@Query(value = "select s.emp_name as name,s.emp_sal as salary from employee s",nativeQuery=true)
//	public List<Employee> getAll();
}
