package com.ali.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ali.payload.commentDTO;
import com.ali.services.commentService;


@RestController
@RequestMapping("api/posts")
public class commentController {
@Autowired	
private commentService service;
	
@GetMapping("/{postID}/comments")	
public ResponseEntity<List<commentDTO>> getall(
		@PathVariable int postID){
	
	return ResponseEntity.ok(this.service.getAllComment(postID));
}
@PostMapping("/{postID}/comments")	
public ResponseEntity<commentDTO> postAll(
		@PathVariable int postID, @RequestBody commentDTO commentDTO){
	return ResponseEntity.ok(this.service.createComment(postID, commentDTO));
}

}
