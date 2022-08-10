package com.example.demoag.service;

import java.util.List;

import com.example.demoag.vo.LoginVO;
import com.example.demoag.vo.RegisterVO;

public interface AdminService {


	boolean saveRegister(RegisterVO registervo);

	boolean checkLogin(LoginVO loginvo);

	List<RegisterVO> retrieveAllDetails();

	RegisterVO retrieveSingleObject(long id);

	boolean deleteSingleObject(long id);

	boolean updateRegister(RegisterVO registervo);

	List<RegisterVO> retrieveUserDetails(long longId);

}
