package com.techboot.BO;

public class LoginBO {
	
	private long id;
	private String name;
	private String email;
	private long mobile;
	private String address;
	
	
	private EmployerBO employerBO;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public EmployerBO getEmployerBO() {
		return employerBO;
	}
	public void setEmployerBO(EmployerBO employerBO) {
		this.employerBO = employerBO;
	}
	
	

}
