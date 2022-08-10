package com.techboot.EmployerDao;

import java.util.List;

import com.techboot.BO.EmployerBO;
import com.techboot.BO.ProjectBO;
import com.techboot.vo.EmployerAttendanceVO;
import com.techboot.vo.EmployerVO;
import com.techboot.vo.LoginVO;
import com.techboot.vo.ProjectVO;

public interface EmployerDao {


	public long createEmployer(EmployerVO employerVO) ;

	public List<EmployerVO> viewEmployer() ;

	long deleteEmployer(EmployerVO employervo) throws Exception;

	public EmployerVO editEmployer(EmployerBO employerBO) throws Exception;

	public long updateEmployer(EmployerVO employerVO)throws Exception;

	public long createEmployerLogin(LoginVO login);

	public long createEmployerAttendance(EmployerAttendanceVO employerAttendanceVO);

	public List<EmployerAttendanceVO> viewEmployerAttendance();

	List<ProjectVO> viewproject();

	long createproject(ProjectVO projectVO);

	

}
