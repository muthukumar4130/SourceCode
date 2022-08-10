package com.example.demoag.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoag.bo.JwtResponse;
import com.example.demoag.jwt.security.JwtUtils;
import com.example.demoag.security.UserDetailsImpl;
import com.example.demoag.service.AdminService;
import com.example.demoag.vo.LoginVO;
import com.example.demoag.vo.RegisterVO;

@RestController
@CrossOrigin(origins = "*")
public class AdminController {
	
	@Autowired
	AdminService adminServices;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/register")
	public boolean insertRegister(@RequestBody RegisterVO registervo ) {
		registervo.setActive(true);
		registervo.setDelete(false);
		boolean check=adminServices.saveRegister(registervo);
		return check;
		
	}
	
	@PostMapping("/login")
	public  ResponseEntity<?> verifyLogin(@RequestBody LoginVO loginvo ) {
		//boolean check=adminServices.checkLogin(loginvo);
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginvo.getEmail(), loginvo.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(new JwtResponse(jwt, 
				 userDetails.getLoginId(),
				 userDetails.getUsername(), 
				 userDetails.getEmail(), 
				 roles));  
		
	}
	
	
	@GetMapping("/viewregister")
	public List<RegisterVO> viewRegister(){
		List<RegisterVO> registerList=new ArrayList<>();
		Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		if(userDetails.getRole().equals("ROLE-ADMIN")) {
			registerList=adminServices.retrieveAllDetails();
		}else {
			long longId=userDetails.getRegId();
			registerList=adminServices.retrieveUserDetails(longId);
		}
		return registerList;
	}

	@GetMapping("/editregister/{id}")
	public RegisterVO editRegister(@PathVariable("id") long id ) {
		RegisterVO register=new RegisterVO();
		try {
		 register=adminServices.retrieveSingleObject(id);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return register;
		
	}

	@GetMapping("/deleteregister/{id}") 
	public boolean deleteRegister(@PathVariable("id") long id) {
		boolean check=adminServices.deleteSingleObject(id);
		return check;   
		
	}
	
	@PostMapping("/updateregister")
	public boolean updateRegister(@RequestBody RegisterVO registervo ) {
		boolean check=false;
		try {
		registervo.setActive(true);
		registervo.setDelete(false);
		check=adminServices.updateRegister(registervo);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return check;
	}
	}
