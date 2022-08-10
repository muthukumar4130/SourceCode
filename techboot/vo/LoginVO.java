package com.techboot.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="login")
public class LoginVO {
	
	
	@Id
	@GeneratedValue		
	private int id;
    @Column(name ="user_name")
	private String username;
	private String password;
	private String UserRole;
	
	@OneToOne
	@JoinColumn(name="emp_id")
	private EmployerVO employerVO;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserRole() {
		return UserRole;
	}

	public void setUserRole(String userRole) {
		UserRole = userRole;
	}

	public EmployerVO getEmployerVO() {
		return employerVO;
	}

	public void setEmployerVO(EmployerVO employerVO) {
		this.employerVO = employerVO;
	}

	
}
