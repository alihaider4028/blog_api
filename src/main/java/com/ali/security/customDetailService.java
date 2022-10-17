package com.ali.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ali.entity.Users;
import com.ali.exception.resourceException;
import com.ali.repository.userRepository;
@Service
public class customDetailService implements UserDetailsService {
@Autowired
userRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	Users user=	this.userRepo.findUserBYEmail(username).orElseThrow(()->new resourceException("username", username, 0));
		
		return user;
	}




}
