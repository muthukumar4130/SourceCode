package com.example.demoag.dao;

import java.util.List;

import com.example.demoag.vo.LoginVO;
import com.example.demoag.vo.RegisterVO;

public interface AdminDao {

	boolean saveRegister(RegisterVO registervo);

	boolean checkLogin(LoginVO loginvo);

	List<RegisterVO> retrieveAllDetails();

	RegisterVO retrieveSingleObject(long id);

	boolean deleteSingleObject(RegisterVO register);

	boolean updateRegister(RegisterVO registervo);

	/* LoginVO retrieveLoginObject(long id); */

	boolean updateLogin(LoginVO loginvo);

	LoginVO Authentication(String username);

	List<RegisterVO> retrieveUserDetails(long longId);

}
