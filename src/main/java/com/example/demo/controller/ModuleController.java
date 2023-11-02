package com.example.demo.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import com.example.demo.repo.ModuleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.pojo.Module;
import com.example.demo.serviceImpl.ModuleServiceImpl;

@RestController
@RequestMapping("/modules")
public class ModuleController {
	
	@Autowired
    private ModuleServiceImpl moduleServiceImpl;
	@Autowired
	private ModuleRepo moduleRepo;

    // REST endpoints for CRUD operations on modules
	@GetMapping("/getAllModules")
	public ResponseEntity<List<Module>> getAllModuleList() throws SQLException,ParseException{
		// call delete employee method
		try {
			List<Module> moduleList = moduleServiceImpl.getAllModules();
			return ResponseEntity.ok(moduleList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/search/{name}")
	public List<Module> findModulesByName(@PathVariable String name){
		return moduleRepo.findByModuleName(name);
	}
}
