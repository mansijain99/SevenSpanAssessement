package com.example.demo.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.pojo.Employee;
import com.example.demo.pojo.Module;
import com.example.demo.repo.EmployeeRepo;
import com.example.demo.repo.ModuleRepo;
import com.example.demo.serviceImpl.EmployeeServiceImpl;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	private static final Logger LOG = LogManager.getLogger();
	
	@Autowired
    private EmployeeServiceImpl employeeServiceImpl;
	
	@Autowired
    private EmployeeRepo employeeRepo;

	@GetMapping("/getAllEmployees")
	public ResponseEntity<List<Employee>> getAllEmployeeList() {
		try {
			List<Employee> empList = employeeServiceImpl.getAllEmployees();
			return ResponseEntity.ok(empList); // Return a 200 OK response with the list of employees
		} catch (Exception e) {
			// Handle the exception and return an appropriate error response
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/getEmployeeById/{id}")
	public ResponseEntity<Employee> getEmpById(@PathVariable("id") long id){
		try {
			Employee emp = employeeServiceImpl.getEmployeeById(id);
			if (emp != null) {
				return ResponseEntity.ok(emp); // Return a 200 OK response with the employee
			} else {
				return ResponseEntity.notFound().build(); // Return a 404 Not Found response
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/addEmployee")
	public String addEmployee(@RequestBody Employee employee){
		// save employee to database
		try {
			employeeServiceImpl.saveEmployee(employee);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOG.debug("Employee with id " + employee.getId() + " is added successfully");
		return "Employee with id " + employee.getId() + " is added successfully";
	}
	
	@PatchMapping("/updateEmployee/{id}")
	public ResponseEntity<Employee> updateEmployeeById(@RequestBody Employee employee, @PathVariable long id){
		// get employee from the service
		Optional<Employee> emp = employeeRepo.findById(id);
		if(emp.isPresent()) {
			Employee employee1 = new Employee();
			employee1.setId(employee.getId());
			employee1.setFirstName(employee.getFirstName());
			employee1.setLastName(employee.getLastName());
			employee1.setPhNumber(employee.getPhNumber());
			employee1.setModules(employee.getModules());
			employee = employeeRepo.save(employee1);
			LOG.debug("Employee with id " + employee.getId() + " is updated successfully");
			return ResponseEntity.ok(employee);
		}else{
			LOG.info("Sorry! Employee is not found");
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/deleteEmployee")
	public String deleteEmployeeById(@RequestBody Employee employee){
		// call delete employee method 
		Optional<Employee> emp = employeeRepo.findById(employee.getId());
		if(emp.isPresent()) {
			employeeRepo.delete(employee);
			LOG.debug("Employee with id " + employee.getId() + " is deleted successfully");
			return "Employee with id " + employee.getId() + " is deleted successfully";
		}
		LOG.info("Sorry! Employee is not found");
		return "Sorry! Employee is not found";
	}
	
	@PostMapping("/getEmployeeModule")
	public ResponseEntity<?> saveEmpRequest(@RequestBody Employee employee){
        //Mapping of employee to module and vice-versa
		return new ResponseEntity<>(employeeServiceImpl.saveEmp(employee), HttpStatus.CREATED);
	}

}
