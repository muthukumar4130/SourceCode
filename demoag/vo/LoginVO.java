package com.example.demoag.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "login")
public class LoginVO{
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String email;
	private String password;
	private String role;
	@OneToOne
    @JoinColumn(name="reg_id")
    private RegisterVO registermapping;
	
	public RegisterVO getRegistermapping() {
		return registermapping;
	}
	public void setRegistermapping(RegisterVO registermapping) {
		this.registermapping = registermapping;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
