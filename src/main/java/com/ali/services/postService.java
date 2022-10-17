package com.ali.services;


import java.util.List;


import com.ali.payload.postDTO;
import com.ali.payload.postResponse;

public interface postService {

//get all posts by userID
	List<postDTO> getAllPostsBYID(int userID);	
//get all posts by categoryID
	List<postDTO> getAllPostsBYCategory(int categoryID);	
//get all posts by category and id 
	List<postDTO> getAllPostsBYCategoryandID(int categoryID,int userID);
//get all posts by posts id
	postDTO getPostBYID(int postID);
//get all posts with keywords
	List<postDTO> getAllPostsWithKeywords(String kewyword);
//get all posts
	postResponse getAllPosts(int pageSize, int pageNumber,String sort,String sortdir);

// create post
	postDTO createPost(postDTO Post, int userId , int categoryID);
//update post
	postDTO updatePost(postDTO Post, int id);
//delete post	
void deletePost(int id);
}
