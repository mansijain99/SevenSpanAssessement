package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.Employee;
import com.example.demo.pojo.Module;
import com.example.demo.repo.EmployeeRepo;
import com.example.demo.repo.ModuleRepo;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl  implements EmployeeService{
	
	private static final Logger LOG = LogManager.getLogger();
	
	@Autowired
    private EmployeeRepo employeeRepository;
    @Autowired
    private ModuleRepo moduleRepository;

    // Implemented CRUD operations for employees and modules

    public List<Employee> getAllEmployees() throws Exception {
        return employeeRepository.findAll();
    }

	@Override
	public void saveEmployee(Employee employee) throws Exception {
		// TODO Auto-generated method stub
		this.employeeRepository.save(employee);
	}

	@Override
	public Employee getEmployeeById(long id) throws Exception{
		// TODO Auto-generated method stub
		Optional<Employee> optional = employeeRepository.findById(id);
		Employee employee = null;
		if (optional.isPresent()) {
			employee = optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + id);
		}
		return employee;
	}

	@Override
	public void deleteEmployeeById(long id) throws Exception{
		// TODO Auto-generated method stub
		this.employeeRepository.deleteById(id);
		
	}
	
	public Employee saveEmp(Employee employee) {
		Employee newEmployee = new Employee();
		newEmployee.setFirstName(employee.getFirstName());
		newEmployee.setLastName(employee.getLastName());
		newEmployee.setPhNumber(employee.getPhNumber());
		newEmployee.setId(employee.getId());
		newEmployee.setModules(employee.getModules());
		return employeeRepository.save(newEmployee);
    	
    }

}
