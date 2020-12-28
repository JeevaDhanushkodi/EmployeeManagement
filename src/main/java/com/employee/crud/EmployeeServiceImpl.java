package com.employee.crud;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> findAll() {
		return (List<Employee>) employeeRepository.findAll();
	}

	@Override
	public Employee findById(int id) {
		return Optional.ofNullable(employeeRepository.findById(id)).get().orElse(null);
	}

	@Override
	public void addemployee(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployee(Employee employee) {
		employeeRepository.delete(employee);
	}

}
