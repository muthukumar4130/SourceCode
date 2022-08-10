 package com.example.demoag.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demoag.dao.AdminDao;
import com.example.demoag.vo.LoginVO;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService  {
	
	@Autowired
	AdminDao adminDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		LoginVO loginvo =adminDao.Authentication(username);
		if(null!=loginvo) {
			return new UserDetailsImpl(loginvo.getName(),loginvo.getRole(),loginvo.getId(),loginvo.getRegistermapping().getId(),loginvo.getEmail(),loginvo.getPassword(),
					getAuthorities(loginvo.getRole()));
		}
			
		return null;
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(String userRole) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(userRole));
		return	authorities;
	}

}
