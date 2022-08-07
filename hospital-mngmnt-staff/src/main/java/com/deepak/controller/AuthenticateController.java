package com.deepak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepak.dtos.EmployeeDTO;
import com.deepak.dtos.Response;
import com.deepak.jwt.JWTUtility;
import com.deepak.jwt.JwtRequest;
import com.deepak.jwt.JwtResponse;
import com.deepak.services.UserServices;
import com.deepak.spring_security.CustomUserDetailsService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/authenticate")
public class AuthenticateController {

	@Autowired
	UserServices services;
	@Autowired
	private JWTUtility jwtUtility;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userService;

	@Autowired
	private CustomUserDetailsService customUserService;

	@PostMapping("/signin")
	public JwtResponse signin(@RequestBody JwtRequest jwtRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}

		final UserDetails userDetails = customUserService.loadUserByUsername(jwtRequest.getEmail());
		final String token = jwtUtility.generateToken(userDetails);

		return new JwtResponse(token);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody EmployeeDTO useData) throws Exception {

		int ch = services.addUser(useData);

		if (ch >= 1) {

			// token generation code
			try {
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(useData.getEmail(), useData.getPassword()));
			} catch (BadCredentialsException e) {
				throw new Exception("INVALID_CREDENTIALS", e);
			}

			final UserDetails userDetails = userService.loadUserByUsername(useData.getEmail());
			final String token = jwtUtility.generateToken(userDetails);

			useData.setToken(token);
			return Response.success(useData);
		}
		return Response.error("Probem in Signup, will wait for some time.");

	}

	@PostMapping("/emailExists")
	public ResponseEntity<?> checkIfEmailExists(@RequestBody EmployeeDTO useData) {
		Boolean emailExists = services.checkIfEmailExists(useData);
		if (emailExists == true)
			return Response.success("DUPLICATE_EMAIL");
		else
			return Response.success("UNIQUE_EMAIL");
	}

}
