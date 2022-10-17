package com.ali.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ali.payload.ApiResponse;
import com.ali.payload.categoryDTO;
import com.ali.services.catService;

@RestController
@RequestMapping("/api/categories")
public class categoryController {
@Autowired
private catService catServive;
@PostMapping("/")
public ResponseEntity<categoryDTO> createcategory(@RequestBody categoryDTO cat){
	categoryDTO catd =this.catServive.createCategory(cat);
	
	return  new ResponseEntity<>(catd,HttpStatus.CREATED);
}
@GetMapping("/")
public ResponseEntity<List<categoryDTO>> getall(){
List<categoryDTO> list = catServive.getALL();
	return new  ResponseEntity<>(list,HttpStatus.OK);}
	
@GetMapping("/{userid}")
public ResponseEntity<categoryDTO> showuserbyid(@PathVariable("userid") int userid){
		
return ResponseEntity.ok(this.catServive.getbyID(userid));	
}

@PutMapping("/{userid}")
public ResponseEntity<categoryDTO> updatebyid(@RequestBody categoryDTO catd, @PathVariable("userid") int userid){
	
return ResponseEntity.ok(this.catServive.updateCategory(catd, userid));	
}
@DeleteMapping("/{userid}")
public ResponseEntity<ApiResponse> delete(@PathVariable("userid") int userid){
	
return new ResponseEntity<ApiResponse>(new ApiResponse("sucessfully deleted",true),HttpStatus.OK);
}
}
