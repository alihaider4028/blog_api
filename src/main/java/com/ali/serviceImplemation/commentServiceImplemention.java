package com.ali.serviceImplemation;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ali.entity.comment;
import com.ali.entity.post;
import com.ali.exception.resourceException;
import com.ali.payload.commentDTO;
import com.ali.payload.postDTO;
import com.ali.services.commentService;
import com.ali.repository.commentRepo;
import com.ali.repository.postRepo;
@Service
public class commentServiceImplemention implements commentService {
@Autowired
private	postRepo postRepo;
@Autowired
private ModelMapper modelMapper;
@Autowired
private commentRepo commentRepo;
	
	@Override
	public commentDTO createComment(int postID, commentDTO commentDTO) {
		post post = this.postRepo.findById(postID).orElseThrow(()-> new resourceException("post","postid",postID));
		comment comment = this.modelMapper.map(commentDTO, comment.class);
		comment.setPost(post);
		comment savedComment = this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment, commentDTO.class);
	}

	@Override
	public commentDTO updateComment(int commentID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteComment(int comment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<commentDTO> getAllComment(int postID) {
		
				return null;
	}

}
