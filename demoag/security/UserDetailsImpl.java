package com.example.demoag.security;
 
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl  implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long loginId;
	private Long regId;

	private String username;

	private String email;

	private String role;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	@JsonIgnore
	private String password;
	
	public UserDetailsImpl(String userName, String role, long loginId,long regId, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.setLoginId(loginId);
		this.setRegId(regId);
		this.username=userName;
		this.setEmail(email);
		this.setRole(role);
		this.password=password;
		this.authorities=authorities;
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}





	public Long getLoginId() {
		return loginId;
	}


	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}


	public Long getRegId() {
		return regId;
	}


	public void setRegId(Long regId) {
		this.regId = regId;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	
}
