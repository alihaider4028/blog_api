package com.ali.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ali.payload.ApiResponse;
import com.ali.payload.UserDTO;
import com.ali.services.userService;
@RestController
@RequestMapping("/api/users")
public class userController {
	@Autowired
	private userService userService;

@PostMapping("/")
public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userdto){
	UserDTO createusetDto= this.userService.userCreate(userdto);
	
	return new ResponseEntity<>(createusetDto,HttpStatus.CREATED);
}
@PutMapping("/{userid}")
public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO user, @PathVariable("userid") int userid){
	UserDTO createusetDto= this.userService.updateUser(user, userid);

	
	return ResponseEntity.ok(createusetDto);
}
@PreAuthorize("hasRole('ADMIN')")
@DeleteMapping("/{userid}")
public ResponseEntity<ApiResponse>DeleteeUser( @PathVariable("userid") int userid){
userService.deleteUser(userid);

	
	return new ResponseEntity<ApiResponse>(new ApiResponse("succesfully delete",true),HttpStatus.OK);
}

@GetMapping("/")
public ResponseEntity<List <UserDTO>> showlist(){
	
return ResponseEntity.ok(this.userService.showlist());	}

@GetMapping("/{userid}")
public ResponseEntity<UserDTO> showuserbyid(@PathVariable("userid") int userid){
	
return ResponseEntity.ok(this.userService.getUserByID(userid));	




}
}
