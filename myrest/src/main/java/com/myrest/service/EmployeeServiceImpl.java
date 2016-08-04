package com.myrest.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myrest.dao.EmployeeDao;
import com.myrest.exception.RecordNotFoundException;
import com.myrest.pojo.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Resource
	private EmployeeDao employeeDao;

	@Override
	@Transactional
	public Employee create(Employee emp) {
		Employee createdShop = emp;
		return employeeDao.save(createdShop);
	}

	@Override
	@Transactional
	public Employee findById(int id) {
		return employeeDao.findOne(id);
	}

	@Override
	@Transactional(rollbackFor = RecordNotFoundException.class)
	public Employee delete(int id) throws RecordNotFoundException {
		Employee deletedShop = employeeDao.findOne(id);

		if (deletedShop == null)
			throw new RecordNotFoundException();

		employeeDao.delete(deletedShop);
		return deletedShop;
	}

	@Override
	@Transactional
	public List<Employee> findAll() {
		return employeeDao.findAll();
	}

	@Override
	@Transactional(rollbackFor = RecordNotFoundException.class)
	public Employee update(Employee emp) throws RecordNotFoundException {
		Employee updatedRec = employeeDao.findOne(emp.getId());

		if (updatedRec == null)
			throw new RecordNotFoundException();

		updatedRec.setName(emp.getName());
		updatedRec.setEmplNumber(emp.getEmplNumber());
		return updatedRec;
	}

	@Override
	public Employee findByName(String name) {
		return employeeDao.findByName(name);
	}

}
