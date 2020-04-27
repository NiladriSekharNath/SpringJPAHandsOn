package com.cognizant.ormlearn.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.repository.EmployeeRepository;
import com.cognizant.ormlearn.service.exception.EmployeeNotFoundException;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Transactional
	public Employee get(int id) throws EmployeeNotFoundException {

		Optional<Employee> findById = employeeRepository.findById(id);
		if (!findById.isPresent())
			throw new EmployeeNotFoundException("Employee Not Found with the ID");
		return findById.get();

	}

	@Transactional
	public void save(Employee employee) {
		employeeRepository.save(employee);

	}
	@Transactional
	public List<Employee> getAllPermanentEmployees() throws EmployeeNotFoundException {
		List<Employee> permanentEmployeesList = employeeRepository.getAllPermanentEmployees();
		if(permanentEmployeesList.isEmpty())
			throw new EmployeeNotFoundException("No Permanent Employees Found");
		return permanentEmployeesList;
	}
	
	@Transactional
	public double getAverageSalary(int id ) throws EmployeeNotFoundException {
		double averageSalary = employeeRepository.getAverageSalary(id);
		if(averageSalary==0.0)
			throw new EmployeeNotFoundException("No Employees Found With DepartmentId:"+id);
		return averageSalary;
		
	}
	@Transactional
	public List<Employee> getAllEmployeesNative() throws EmployeeNotFoundException {
		List<Employee> employeesList = employeeRepository.getAllEmployeesNative();
		if (employeesList.isEmpty())
			throw new EmployeeNotFoundException("Employee List Empty, Check EmployeeRepository Fucntion");
		return employeesList;
	}
}
