package com.deepak.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deepak.custom_exception.EmployeeAlreadyExistsException;
import com.deepak.dtos.EmployeeDTO;
import com.deepak.entities.Employee;
import com.deepak.entities.User;
import com.deepak.repository.UserRepository;

@Service
@Transactional
public class UserServices {
	@Autowired
	UserRepository userDao;

	public List<User> findAllUsers() {
		return userDao.findAll();
	}

	public User findUserById(int userId) {
		return userDao.getById(userId);
	}

	public User findUserByEmail(String email) {
		return userDao.findByEmail(email);

	}

	public Boolean checkIfEmailExists(EmployeeDTO userData) {
		return userDao.existsByEmail(userData.getEmail());
	}

	public int addUser(EmployeeDTO useData) {
		if (!userDao.existsByEmail(useData.getEmail())) {
			User user = new User();
			user.setEmail(useData.getEmail());
			user.setFirstName(useData.getFirstName());
			user.setLastName(useData.getLastName());
			user.setPassword(useData.getPassword());
			user.setPhoneNumber(useData.getPhoneNumber());
			user.setRole(useData.getRole());

			Employee emp = new Employee();
			emp.setDob(useData.getDob());
			emp.setJoiningDate(useData.getJoiningDate());
			user.addEmployee(emp);
			User dbUser = userDao.save(user);

			if (dbUser != null)
				return 1;
		}
		throw new EmployeeAlreadyExistsException("Employee with email " + useData.getEmail() + " already exists!!!");
	}
}
