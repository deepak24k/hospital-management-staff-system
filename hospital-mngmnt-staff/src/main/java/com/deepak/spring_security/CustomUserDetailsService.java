package com.deepak.spring_security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.deepak.entities.User;
import com.deepak.repository.UserRepository;
import com.deepak.services.UserServices;
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userDao;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userDao.findByEmail(email);
		if(user!=null) {
			return user;
		}
		return null;
	}

}
