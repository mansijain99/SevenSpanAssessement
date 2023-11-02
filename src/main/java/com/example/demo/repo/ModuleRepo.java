package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.Module;

@Repository
public interface ModuleRepo extends JpaRepository<Module, Long> {
	List<Module> findByModuleName(String moduleName);
	
}
