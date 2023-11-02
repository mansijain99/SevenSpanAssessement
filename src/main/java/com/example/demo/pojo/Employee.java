package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="EMPLOYEE")
public class Employee {
	@Id
	@GeneratedValue (strategy =GenerationType.AUTO,generator = "increment")
	@Column(name = "EMP_ID")
	private Long id;
	
	@Column(name = "FIRST_NAME")
    private String firstName;
	
	@Column(name = "LAST_NAME")
    private String lastName;
	
	@Column(name = "PHONE_NUMBER")
    private String phNumber;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "EMPLOYEE_MODULE", 
	joinColumns = {
			@JoinColumn(name = "employee_id", referencedColumnName = "EMP_ID"),
	},
	inverseJoinColumns = {
			@JoinColumn(name = "module_id", referencedColumnName = "MOD_ID")
	})
	private Collection<Module> modules;
	 
	
	public Employee() {
	}
    
	public Employee(long l, String string, String string2, String string3) {
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhNumber() {
		return phNumber;
	}
	public void setPhNumber(String phNumber) {
		this.phNumber = phNumber;
	}

	public Collection<Module> getModules() {
		return modules;
	}

	public void setModules(Collection<Module> modules) {
		this.modules = modules;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName 
				+ ", phNumber=" + phNumber + "]";
	}
}
