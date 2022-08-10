package com.techboot.EmployerDao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.techboot.BO.EmployerBO;
import com.techboot.BO.ProjectBO;
import com.techboot.vo.EmployerAttendanceVO;
import com.techboot.vo.EmployerVO;
import com.techboot.vo.LoginVO;
import com.techboot.vo.ProjectVO;

@Repository
public class EmployerDaoImpl implements EmployerDao{

		@Autowired
		  SessionFactory sessionfactory;
			
			@Override
			public long createEmployer(EmployerVO employerVO)  {
				long count = 0;
				try{
					 Session session=sessionfactory.getCurrentSession();
					 count=(long)session.save(employerVO);
				   }catch (Exception e) {
		            System.out.println(e);						
				  }
				   return count;

		}

			@Override
			public List<EmployerVO> viewEmployer() {
				
				 Session session=sessionfactory.getCurrentSession();
				 Criteria criteria=session.createCriteria(EmployerVO.class);
				 List<EmployerVO> voList=criteria.list();
			
				return voList;
			}


			@Override
			public EmployerVO editEmployer(EmployerBO employerBO) throws Exception {
				 
			     EmployerVO empVO=new EmployerVO();
				 Session session=sessionfactory.getCurrentSession();
				 Criteria criteria=session.createCriteria(EmployerVO.class);
				 criteria.add(Restrictions.eq("id",employerBO.getId()));
				 empVO=(EmployerVO)criteria.uniqueResult();
				 return empVO;
			}

			@Override
			public long updateEmployer(EmployerVO employerVO) {
				long empId=0;
				try {
				Session session=sessionfactory.getCurrentSession();
				 session.saveOrUpdate(employerVO);
				 empId=1;
				}catch(Exception e) {
					e.printStackTrace();
				}
				return empId;
			}

			@Override
			public long deleteEmployer(EmployerVO employerVO){
				long empid=0;
				try {
				Session session=sessionfactory.getCurrentSession();
				 session.delete(employerVO);
				 empid=1;
				}catch(Exception e) {
					e.printStackTrace();
				}
					
				return empid;
			}

			@Override
			public long createEmployerLogin(LoginVO login) {
			
				long count1 = 0;
				try{
					 Session session=sessionfactory.getCurrentSession();
					 count1=(long)session.save(login);

				   }catch (Exception e) {
		            System.out.println(e);
						
				  }
				   return count1;

			}

			@Override
			public long createEmployerAttendance(EmployerAttendanceVO employerAttendanceVO) {
				long count = 0;
				try{
					 Session session=sessionfactory.getCurrentSession();
					 count=(long)session.save(employerAttendanceVO);

				   }catch (Exception e) {
		            System.out.println(e);
						
				  }
				   return count;
			}

			@Override
			public List<EmployerAttendanceVO> viewEmployerAttendance() {
				 
				Session session=sessionfactory.getCurrentSession();
				 Criteria criteria=session.createCriteria(EmployerAttendanceVO.class);
				 List<EmployerAttendanceVO> voList=criteria.list();
			
				return voList;
			}
			
			@Override
			public long createproject(ProjectVO projectVO)  {
				long count = 0;
				try{
					 Session session=sessionfactory.getCurrentSession();
					 count=(long)session.save(projectVO);

				   }catch (Exception e) {
		            System.out.println(e);
						
				  }
				   return count;

		}

			@Override
			public List<ProjectVO> viewproject() {
				
				 Session session=sessionfactory.getCurrentSession();
				 Criteria criteria=session.createCriteria(ProjectVO.class);
				 List<ProjectVO> voList=criteria.list();
			
				return voList;
			}
	
	}

