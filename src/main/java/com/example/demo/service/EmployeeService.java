package com.example.demo.service;

import java.util.List;

import com.example.demo.pojo.Employee;

public interface EmployeeService {	
	
	List<Employee> getAllEmployees() throws Exception;
	void saveEmployee(Employee employee) throws Exception;
	Employee getEmployeeById(long id) throws Exception;
	void deleteEmployeeById(long id)throws Exception;
}
