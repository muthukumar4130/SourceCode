package com.example.demoag.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demoag.vo.LoginVO;
import com.example.demoag.vo.RegisterVO;
@Repository
public class AdminDaoImpl implements AdminDao {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean saveRegister(RegisterVO registervo) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		long regid= (long) session.save(registervo);
		registervo.setId(regid);
		if(0<regid) {
			LoginVO loginvo=new LoginVO();
			loginvo.setId(regid);
			loginvo.setEmail(registervo.getEmail());
			loginvo.setName(registervo.getName());
			loginvo.setPassword(registervo.getPassword());
			loginvo.setRole("ROLE-USER");
			loginvo.setRegistermapping(registervo);
			long loginid= (long) session.save(loginvo);
			if(0<loginid) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean checkLogin(LoginVO loginvo) {
		// TODO Auto-generated method stub
		LoginVO login = new LoginVO();
		Session session=sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<LoginVO> query = builder.createQuery(LoginVO.class);
        Root<LoginVO> root = query.from(LoginVO.class);
        query.select(root).where(builder.equal(root.get("email"), loginvo.getEmail()));
        query.select(root).where(builder.equal(root.get("password"), loginvo.getPassword()));
        Query<LoginVO> q=session.createQuery(query);
        login=q.getSingleResult();
        if(null!=login) {
			return true;
        }
		return false;
	}

	@Override
	public List<RegisterVO> retrieveAllDetails() {
		// TODO Auto-generated method stub
		List<RegisterVO> registerList=new ArrayList<>();
		Session session=sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<RegisterVO> query = builder.createQuery(RegisterVO.class);
        Root<RegisterVO> root = query.from(RegisterVO.class);
        query.select(root).where(builder.equal(root.get("isActive"), true),builder.equal(root.get("isDelete"), false));
        //query.select(root).where(builder.equal(root.get("isDelete"), false));
        Query<RegisterVO> q=session.createQuery(query);
        registerList=q.getResultList();
		List<RegisterVO> registerLists=new ArrayList<>();

        if(null!=registerList) {
        	long sno=1;
        	for(RegisterVO register:registerList) {
        		register.setSno(sno);
        		registerLists.add(register);
        		sno++;
        	}
        }
		return registerLists;
	}

	@Override
	public RegisterVO retrieveSingleObject(long id) {
		// TODO Auto-generated method stub
		RegisterVO register=new RegisterVO();
		Session session=sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<RegisterVO> query = builder.createQuery(RegisterVO.class);
        Root<RegisterVO> root = query.from(RegisterVO.class);
        query.select(root).where(builder.equal(root.get("isActive"), true));
        query.select(root).where(builder.equal(root.get("id"), id));
        Query<RegisterVO> q=session.createQuery(query);
        register=q.getSingleResult();
		return register ;
	}

	@Override
	public boolean deleteSingleObject(RegisterVO register) {
		// TODO Auto-generated method stub
		try {
		Session session=sessionFactory.getCurrentSession();
		session.update(register);
		return true;
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	@Override
	public boolean updateRegister(RegisterVO registervo) {
		// TODO Auto-generated method stub
		try {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(registervo);
				
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
		return true;
	}

	@Override
	public boolean updateLogin(LoginVO loginvo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LoginVO Authentication(String username) {
		// TODO Auto-generated method stub
		LoginVO login = new LoginVO();
		Session session=sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<LoginVO> query = builder.createQuery(LoginVO.class);
        Root<LoginVO> root = query.from(LoginVO.class);
        query.select(root).where(builder.equal(root.get("email"), username));
      //  query.select(root).where(builder.equal(root.get("password"), loginvo.getPassword()));
        Query<LoginVO> q=session.createQuery(query);
        login=q.getSingleResult();
        if(null!=login) {
			return login;
        }
		return new LoginVO();
	}

	@Override
	public List<RegisterVO> retrieveUserDetails(long longId) {
	
		List<RegisterVO> registerList=new ArrayList<>();
	try {
	Session session=sessionFactory.getCurrentSession();
	CriteriaBuilder builder = session.getCriteriaBuilder();
    CriteriaQuery<RegisterVO> query = builder.createQuery(RegisterVO.class);
    Root<RegisterVO> root = query.from(RegisterVO.class);
    query.select(root).where(builder.equal(root.get("isActive"), true),
    		builder.equal(root.get("isDelete"), false),
    		builder.equal(root.get("id"), longId));
    //query.select(root).where(builder.equal(root.get("isDelete"), false));
    Query<RegisterVO> q=session.createQuery(query);
    registerList=q.getResultList();
	List<RegisterVO> registerLists=new ArrayList<>();

    if(null!=registerList) {
    	long sno=1;
    	for(RegisterVO register:registerList) {
    		register.setSno(sno);
    		registerLists.add(register);
    		sno++;
    	}
    }
    return registerLists;
    }
	catch (Exception e) {
		System.out.print(e);
	}
	return null;
	
	}

}
  