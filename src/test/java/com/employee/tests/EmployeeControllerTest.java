package com.employee.tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.employee.crud.Employee;
import com.employee.crud.EmployeeRepository;
import com.employee.crud.EmployeeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

	@InjectMocks EmployeeServiceImpl employeeServiceImpl;
	@Mock EmployeeRepository employeerepository;

	@Test
	public void getAllEmployeesTest() {
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(new Employee(6, "Jeeva", "Bangalore", 23, "SoftwareEngineer"));
		employeeList.add(new Employee(7, "peter", "Chennai", 24, "Devloper"));
		employeeList.add(new Employee(8, "Naveen", "Pune", 25, "Tester"));
		when(employeerepository.findAll()).thenReturn(employeeList);
		List<Employee> empList = employeeServiceImpl.findAll();
		assertEquals(3, empList.size());
		verify(employeerepository, times(1)).findAll();
	}

	@Test
	public void getEmployeeByIdTest() {
		Optional<Employee> employee = employeerepository.findById(1);
		if (employee.isPresent()) {
			when(employee.get()).thenReturn(new Employee(6, "Jeeva", "Bangalore", 23, "SoftwareEngineer"));
		}
		Employee emp = employeeServiceImpl.findById(6);
		if (emp != null) {
			assertEquals("Lokesh", emp.getName());
			assertEquals("20", emp.getAge());
			assertEquals("Enginner", emp.getOccupation());
		}
	}

	@Test
	public void createEmployeeTest() {
		Employee employee = new Employee(6, "Jeeva", "Bangalore", 23, "SoftwareEngineer");
		employeeServiceImpl.addemployee(employee);
		verify(employeerepository, times(1)).save(employee);
	}

	@Test
	public void deleteEmployeeTest() {
		Employee employee = new Employee(6, "Jeeva", "Bangalore", 23, "SoftwareEngineer");
		employeeServiceImpl.deleteEmployee(employee);
		verify(employeerepository, times(1)).delete(employee);
	}
}
