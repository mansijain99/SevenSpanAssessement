package com.example.demo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.Employee;
import com.example.demo.pojo.Module;
import com.example.demo.repo.ModuleRepo;
import com.example.demo.service.ModuleService;

@Service
public class ModuleServiceImpl implements ModuleService{
	
	@Autowired
    private ModuleRepo moduleRepository;
	
	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;

    // Implement CRUD operations for modules

    public List<Module> getAllModules() {
        return moduleRepository.findAll();
    }
}
