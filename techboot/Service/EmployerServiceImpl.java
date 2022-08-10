package com.techboot.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techboot.BO.EmployerAttendanceBO;
import com.techboot.BO.EmployerBO;
import com.techboot.EmployerDao.EmployerDao;
import com.techboot.vo.ProjectVO;
import com.techboot.BO.ProjectBO;
import com.techboot.vo.EmployerAttendanceVO;
import com.techboot.vo.EmployerVO;
import com.techboot.vo.LoginVO;

@Service
@Transactional
public class EmployerServiceImpl implements EmployerService{
    
	@Autowired
	EmployerDao employerDao;

	@Override
	public long createEmployer(EmployerBO employerBO)   {
		
		long empId=0;
		long count=0;
		
		if(null!=employerBO) {
			EmployerVO empVO=new EmployerVO();
			empVO.setName(employerBO.getName());
			empVO.setEmail(employerBO.getEmail());
			empVO.setMobile(employerBO.getMobile());
			empVO.setAddress(employerBO.getAddress());
		empId=employerDao.createEmployer(empVO);
		
		if(empId>0){
			empVO.setId(empId);
			LoginVO login=new LoginVO();
			login.setUsername(employerBO.getEmail());
			login.setPassword("techboot");
			login.setUserRole("ROLE-EMPLOYER");
			login.setEmployerVO(empVO);
			count=employerDao.createEmployerLogin(login);
		}
		}
		
		return count;
	}
	@Override
	public List<EmployerBO> viewEmployer() {

		List<EmployerBO> boList=new ArrayList<>();
		List<EmployerVO> voList=employerDao.viewEmployer();
		
		if(null!=voList) {
		for(EmployerVO empVO:voList) {
			EmployerBO empBO=new EmployerBO();
			empBO.setName(empVO.getName());
			empBO.setAddress(empVO.getAddress());
			empBO.setEmail(empVO.getEmail());
			empBO.setMobile(empVO.getMobile());
			empBO.setId(empVO.getId());
			boList.add(empBO);
		}
		
		}
		return boList;
	}
	
	@Override
	public EmployerBO editEmployer(EmployerBO employerBO) throws Exception {
		EmployerBO empBO=new EmployerBO();
		EmployerVO empVO=employerDao.editEmployer(employerBO);
		if(null!=empVO) {
			empBO.setName(empVO.getName());
			empBO.setAddress(empVO.getAddress());
			empBO.setEmail(empVO.getEmail());
			empBO.setMobile(empVO.getMobile());
			empBO.setId(empVO.getId());
		}
		return empBO;
	}
	@Override
	public long updateEmployer(EmployerBO employerBO) throws Exception{
		long count=0;
		if(null!=employerBO) {
			EmployerVO empVO=new EmployerVO();
			empVO.setName(employerBO.getName());
			empVO.setAddress(employerBO.getAddress());
			empVO.setEmail(employerBO.getEmail());
			empVO.setMobile(employerBO.getMobile());
			empVO.setId(employerBO.getId());
			count=employerDao.updateEmployer(empVO);
		}
		return count;
	}
	@Override
	public long deleteEmployer(EmployerBO employerBO) throws Exception {
		long count=0;
		if(null!=employerBO) {
			EmployerVO empVO=new EmployerVO();
			empVO.setId(employerBO.getId());
			count=employerDao.deleteEmployer(empVO);
		}
		return count;
	}
	
	@Override
	public long createEmployerAttendance(EmployerAttendanceBO employerAttendanceBO) {
		EmployerAttendanceVO employerAttendanceVO=new EmployerAttendanceVO();
		long attendanceId=0;
		if(null!=employerAttendanceBO) {
			employerAttendanceVO.setId(employerAttendanceBO.getId());
			employerAttendanceVO.setName(employerAttendanceBO.getName());
			employerAttendanceVO.setDate(employerAttendanceBO.getDate());
			employerAttendanceVO.setStatus(employerAttendanceBO.getStatus());
			employerAttendanceVO.setInTime(employerAttendanceBO.getInTime());
			employerAttendanceVO.setOutTime(employerAttendanceBO.getOutTime());
			EmployerVO employerVO=new EmployerVO();
			employerVO.setId(1);
			employerAttendanceVO.setEmployerVO(employerVO);

			
			attendanceId=employerDao.createEmployerAttendance(employerAttendanceVO);
			
		}
		return attendanceId; 
	}
	
	@Override
	public List<EmployerAttendanceBO> viewEmployerAttendance(){
		List<EmployerAttendanceBO> boList=new ArrayList<>();
		List<EmployerAttendanceVO> EmployerAttendanceVOList=employerDao.viewEmployerAttendance();
		if(null!=EmployerAttendanceVOList) {
			for(EmployerAttendanceVO employerAttendanceVO:EmployerAttendanceVOList) {
				EmployerAttendanceBO employerAttendanceBO=new EmployerAttendanceBO();
				
				employerAttendanceBO.setId(employerAttendanceVO.getId());
				employerAttendanceBO.setName(employerAttendanceVO.getName());
				employerAttendanceBO.setDate(employerAttendanceVO.getDate());
				employerAttendanceBO.setInTime(employerAttendanceVO.getInTime());
				employerAttendanceBO.setOutTime(employerAttendanceVO.getOutTime());
				employerAttendanceBO.setStatus(employerAttendanceVO.getStatus());
				
				EmployerVO empVO=employerAttendanceVO.getEmployerVO();
				EmployerBO empBO=new EmployerBO();
				empBO.setName(empVO.getName());
				empBO.setAddress(empVO.getAddress());
				empBO.setEmail(empVO.getEmail());
				empBO.setMobile(empVO.getMobile());
				empBO.setId(empVO.getId());
				
				employerAttendanceBO.setEmployerBO(empBO);
				boList.add(employerAttendanceBO);								
			}
		}
		return boList;
	}
	
	@Override
	public long createproject(ProjectBO projectBO)   {
		
		long projectId=0;
		long count=0;
		
		if(null!=projectBO) {
			ProjectVO projectVO=new ProjectVO();
			projectVO.setProjectId(projectBO.getProjectId());
			projectVO.setProjectName(projectBO.getProjectName());
			projectVO.setStartDate(projectBO.getStartDate());
			projectVO.setEndDate(projectBO.getEndDate());
			projectVO.setProjectReview(projectBO.getProjectReview());
			projectVO.setProjectType(projectBO.getProjectType());
			projectVO.setEmployerId(projectBO.getEmployerId());
			List<EmployerVO> listEmployer=new ArrayList<>();
			String[] employerId=projectBO.getEmployerId().split(",");
			for(String empId:employerId) {
				EmployerVO employerVO=new EmployerVO();
				long id=Long.parseLong(empId);
				employerVO.setId(id);
				listEmployer.add(employerVO);
			}
			projectVO.setListEmployer(listEmployer);
			projectId=employerDao.createproject(projectVO);
	}
		return count;
	}
	@Override
	public List<ProjectBO> viewproject() {
		List<ProjectBO> boList=new ArrayList<>();
		List<ProjectVO> voList=employerDao.viewproject();
		
		if(null!=voList) {
		for(ProjectVO projectVO:voList) {
			ProjectBO projectBO=new ProjectBO();
			projectBO.setProjectId(projectVO.getProjectId());
			projectBO.setProjectName(projectVO.getProjectName());
			projectBO.setStartDate(projectVO.getStartDate());
			projectBO.setEndDate(projectVO.getEndDate());
			projectBO.setProjectReview(projectVO.getProjectReview());
			projectBO.setProjectType(projectVO.getProjectType());
			boList.add(projectBO);
		}
		
		}
		return boList;
	}
}
	
	
	
