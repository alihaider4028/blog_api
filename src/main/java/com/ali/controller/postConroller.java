package com.ali.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ali.payload.ApiResponse;
import com.ali.payload.postDTO;
import com.ali.payload.postResponse;
import com.ali.serviceImplemation.fileServiceImpel;
import com.ali.serviceImplemation.postServiceImplemention;


@RestController
@RequestMapping("/api/posts/")
public class postConroller {
@Autowired	
private	postServiceImplemention service;
@Autowired
private fileServiceImpel fileService;
@Value("${project.image}")
private String path;
	
@GetMapping("/")	
public ResponseEntity<postResponse> getall(
		@RequestParam(value= "pageNumber", defaultValue = "0", required = false)int pageNumber, 
		@RequestParam(value ="pageSize",defaultValue = "5", required = false) int pageSize,
		@RequestParam(value = "sortBY", defaultValue ="title",required =false) String sortBY,
		@RequestParam(value = "sortdir", defaultValue ="asc",required =false) String sortdir

		){
	
	return  ResponseEntity.ok(this.service.getAllPosts(pageSize,pageNumber,sortBY,sortdir));
}
@GetMapping("user/{userID}")	
public ResponseEntity<List<postDTO>> getallbyuser(@PathVariable int userID){
	
	return  ResponseEntity.ok(this.service.getAllPostsBYID(userID));
}

@GetMapping("category/{categoryID}")	
public ResponseEntity<List<postDTO>> getallbycategory(@PathVariable int categoryID){
	
	return  ResponseEntity.ok(this.service.getAllPostsBYCategory(categoryID));
}
@GetMapping("keyword/{keyword}")
public ResponseEntity<List<postDTO>> getallbyPostByKeyword(@PathVariable String keyword){
	
	return  ResponseEntity.ok(this.service.getAllPostsWithKeywords(keyword));
}
@PostMapping("user/{userId}/category/{categoryID}")
public ResponseEntity<postDTO> createPost(@RequestBody postDTO Post, @PathVariable int userId , @PathVariable int categoryID)
{
	return new ResponseEntity<postDTO>(this.service.createPost(Post, userId, categoryID),HttpStatus.OK);
}
@GetMapping("/{postID}")	
public ResponseEntity<postDTO> getPostBYID( @PathVariable int postID){
	
	return ResponseEntity.ok(this.service.getPostBYID(postID));
}
@PutMapping("/{postID}")
public ResponseEntity<ApiResponse> updatePost(@RequestBody postDTO post, @PathVariable int postID)
{
	this.service.updatePost(post, postID);
return new ResponseEntity<ApiResponse>(new ApiResponse("succesfully updated",true),HttpStatus.OK);
}
@DeleteMapping("/{postID}")
public ResponseEntity<ApiResponse> deletePost( @PathVariable int postID)
{
	this.service.deletePost(postID);
return new ResponseEntity<ApiResponse>(new ApiResponse("succesfully deleted",true),HttpStatus.OK);
}
@PostMapping("/images/postID/{postID}")
public ResponseEntity<postDTO> uploadimages
(@RequestParam("image") MultipartFile image, @PathVariable("postID") int postID ) throws IOException{
	postDTO post = this.service.getPostBYID(postID);
	Logger LOGGER=LoggerFactory.getLogger(postConroller.class);

	String fileName= this.fileService.uploadimage(path, image);
	post.setImageName(fileName);
	LOGGER.info(String.format("message info -> %s", fileName));
	postDTO updatedPost = this.service.updatePost(post, postID);
	
return new ResponseEntity<postDTO>(updatedPost,HttpStatus.OK);}

}
