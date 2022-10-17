package com.ali.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ali.exception.resourceException;
import com.ali.payload.ApiResponse;
import com.ali.payload.UserDTO;
import com.ali.payload.jwtAuthRequest;
import com.ali.payload.jwtAuthResponse;
import com.ali.security.JwtTokenHelper;
import com.ali.services.userService;
@RestController
@RequestMapping("api/vi/auth")
public class AuthController {
	
@Autowired
private UserDetailsService userDetailService;
@Autowired
private AuthenticationManager authaticationManger;
@Autowired
private JwtTokenHelper jwtTokenHelper;
@Autowired
private userService userService;


	@PostMapping("/login")
public ResponseEntity<jwtAuthResponse> createToken(@RequestBody jwtAuthRequest request) throws Exception{
	this.authaticate(request.getUsername(),request.getPassword());
	UserDetails userDetails = this.userDetailService.loadUserByUsername(request.getUsername());
	String token = this.jwtTokenHelper.generateToken(userDetails);
	jwtAuthResponse response = new jwtAuthResponse();
	response.setToken(token);
return	new ResponseEntity<>(response,HttpStatus.OK);
}

	private void authaticate(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
			try {
				this.authaticationManger.authenticate(token);
			} catch (Exception e) {
				// TODO Auto-generated catch block

			throw new resourceException(username, password, 0);
			}

		
		
	}	
	@PostMapping("/register")
	public ResponseEntity<UserDTO> resgister(@Valid @RequestBody UserDTO userdto){
		
		UserDTO createusetDto= this.userService.registerNewUser(userdto);
		
		return new ResponseEntity<>(createusetDto,HttpStatus.CREATED);
	}
	
	
	
}
