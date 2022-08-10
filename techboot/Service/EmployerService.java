package com.techboot.Service;

import java.util.List;

import com.techboot.BO.EmployerAttendanceBO;
import com.techboot.BO.EmployerBO;
import com.techboot.BO.ProjectBO;

public interface EmployerService {


	long createEmployer(EmployerBO employerBO) ;

	List<EmployerBO> viewEmployer();

	long deleteEmployer(EmployerBO employerBO)throws Exception;

	EmployerBO editEmployer(EmployerBO employerBO)throws Exception;

	long updateEmployer(EmployerBO employerBO)throws Exception;

	long createEmployerAttendance(EmployerAttendanceBO employerAttendanceBO);

	List<EmployerAttendanceBO> viewEmployerAttendance();

	long createproject(ProjectBO projectBO);

	List<ProjectBO> viewproject();

}
