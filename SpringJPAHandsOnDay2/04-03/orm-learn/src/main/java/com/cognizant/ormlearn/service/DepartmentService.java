package com.cognizant.ormlearn.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.repository.DepartmentRepository;
import com.cognizant.ormlearn.service.exception.DepartmentNotFoundException;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Transactional
	public Department get(int id) throws DepartmentNotFoundException {

		Optional<Department> findById = departmentRepository.findById(id);
		if (!findById.isPresent())
			throw new DepartmentNotFoundException("Department Not Found By the Id");
		return findById.get();

	}

	@Transactional
	public void save(Department department) {
		departmentRepository.save(department);

	}

}
