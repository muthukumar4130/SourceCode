package com.example.demoag.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demoag.dao.AdminDao;
import com.example.demoag.vo.LoginVO;
import com.example.demoag.vo.RegisterVO;
@Service
@Transactional
public class AdminServiceImpl implements AdminService {
	 
	@Autowired
	AdminDao adminDao;
	
	@Autowired
	PasswordEncoder encoder;

	@Override
	public boolean saveRegister(RegisterVO registervo) {
		// TODO Auto-generated methodstub;
		boolean check=false;
		if(null!=registervo) {
			//BeanUtils.copyProperties(registerbo, registervo);
			registervo.setPassword(encoder.encode(registervo.getPassword()));
			
			check=adminDao.saveRegister(registervo);
		}
		return check;
	}

	@Override
	public boolean checkLogin(LoginVO loginvo) {
		// TODO Auto-generated methodstub;
		boolean check=false;
		if(null!=loginvo) {
			//BeanUtils.copyProperties(registerbo, registervo);
			check=adminDao.checkLogin(loginvo);
		}
		return check;
	}

	@Override
	public List<RegisterVO> retrieveAllDetails() {
		// TODO Auto-generated method stub
		return adminDao.retrieveAllDetails();
	}

	@Override
	public RegisterVO retrieveSingleObject(long id) {
		// TODO Auto-generated method stub
		return adminDao.retrieveSingleObject(id);
	}

	@Override
	public boolean deleteSingleObject(long id) {
		// TODO Auto-generated method stub
		RegisterVO register= adminDao.retrieveSingleObject(id);
		register.setActive(false);
		register.setDelete(true);
		return adminDao.deleteSingleObject(register);
	}

	@Override
	public boolean updateRegister(RegisterVO registervo) {
		// TODO Auto-generated method stub
		boolean result=false;
		try {
			if(null!=registervo) {
				 result=adminDao.updateRegister(registervo);
			}
		
		} catch (Exception e) {
		}
		return result;
	}

	@Override
	public List<RegisterVO> retrieveUserDetails(long longId) {
		// TODO Auto-generated method stub
		return adminDao.retrieveUserDetails(longId);
	}

	
}
