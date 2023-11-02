package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="MODULE")
public class Module {
	@Id
    @GeneratedValue(strategy =GenerationType.AUTO,generator = "increment")
	@Column(name = "MOD_ID")
    private Long id;
	
	@Column(name = "MODULE_NAME")
    private String moduleName;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy="modules")
	@JsonIgnore
	private Collection<Employee> employee = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public Collection<Employee> getEmployee() {
		return employee;
	}
	public void setEmployee(Collection<Employee> employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Module [id=" + id + ", moduleName=" + moduleName + "]";
	}

}
