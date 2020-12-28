package com.employee.crud;

import java.util.List;

public interface EmployeeService {

	List<Employee> findAll();
	
	Employee findById(int id);

	void addemployee(Employee employee);

	void deleteEmployee(Employee employee);
}
