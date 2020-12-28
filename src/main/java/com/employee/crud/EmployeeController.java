package com.employee.crud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired private EmployeeService employeeService;

	@GetMapping
	public List<Employee> getEmployees() {
		return employeeService.findAll();
	}

	@GetMapping("/{employeeId}")
	public Employee getEmployee(@PathVariable Integer employeeId) throws RecordNotFoundException {
		Employee employee = employeeService.findById(employeeId);
		if (employee == null) {
			throw new RecordNotFoundException("No employee record exist for given id");
		}
		return employee;
	}
	
	@PostMapping("/addEmployee")
	public ResponseEntity<?> saveEmployee(@RequestBody Employee employee) {
		employeeService.addemployee(employee);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@DeleteMapping("/deleteEmployee/{employeeId}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Integer employeeId) throws RecordNotFoundException {
		Employee employee = employeeService.findById(employeeId);
		if (employee == null) {
			throw new RecordNotFoundException("No employee record exist for given id");
		}
		employeeService.deleteEmployee(employee);
		return ResponseEntity.noContent().build();
	}
}
