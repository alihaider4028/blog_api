package com.ali.serviceImplemation;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ali.entity.Users;
import com.ali.entity.category;
import com.ali.entity.post;
import com.ali.exception.resourceException;
import com.ali.payload.postDTO;
import com.ali.payload.postResponse;
import com.ali.repository.catergoryRepository;
import com.ali.repository.postRepo;
import com.ali.repository.userRepository;
import com.ali.services.postService;

@Service
public class postServiceImplemention implements postService {
	@Autowired
	private postRepo postRepo;
	@Autowired
	private ModelMapper modelmapper;
	@Autowired
	private userRepository userRepo;
	@Autowired
	private catergoryRepository cat;

	@Override
	public List<postDTO> getAllPostsBYCategory(int categoryID) {
		category categ = this.cat.findById(categoryID)
				.orElseThrow(() -> new resourceException("user", "id", categoryID));
		List<post> list = this.postRepo.findbyCategory(categ.getCategoryId());
		List<postDTO> listD = list.stream().map(post -> modelmapper.map(post, postDTO.class))
				.collect(Collectors.toList());
		return listD;

	}

	@Override
	public List<postDTO> getAllPostsBYCategoryandID(int categoryID, int userID) {
		Users use = this.userRepo.findById(userID).orElseThrow(() -> new resourceException("user", "id", userID));
		List<post> list = this.postRepo.findbyUser(use.getId());
		List<postDTO> listD = list.stream()
				.filter(post -> post.getUser().getId() == userID && post.getCategory().getCategoryId() == categoryID)
				.map(post -> modelmapper.map(post, postDTO.class)).collect(Collectors.toList());
		if (listD.isEmpty()) {
			throw new resourceException("user havent posted anything", " following id", userID);

		}
		return listD;
	}

	@Override
	public postDTO getPostBYID(int postID) {
		post Post = this.postRepo.findById(postID).orElseThrow(() -> new resourceException("post", "id", postID));
		return this.modelmapper.map(Post, postDTO.class);
	}

	@Override
	public List<postDTO> getAllPostsWithKeywords(String kewyword) {
		List<post> list = this.postRepo.findAll();
		List<postDTO> listD = list.stream().filter(post -> post.getTitle().contains(kewyword))
				.map(post -> modelmapper.map(post, postDTO.class)).collect(Collectors.toList());
		if (listD.isEmpty()) {
			throw new resourceException("user havent posted anything", " following keywords", kewyword);

		}

		return listD;
	}

	@Override
	public postDTO createPost(postDTO Post, int userId, int categoryID) {
		// TODO Auto-generated method stub
		Users use = this.userRepo.findById(userId).orElseThrow(() -> new resourceException("user", "id", userId));
		category categ = this.cat.findById(categoryID)
				.orElseThrow(() -> new resourceException("user", "id", categoryID));
		post Postd = this.modelmapper.map(Post, post.class);
		Postd.setImageName("default.png");
		Postd.setAddedDate(new Date());
		Postd.setUser(use);
		Postd.setCategory(categ);

		post savedPost = this.postRepo.save(Postd);
		return this.modelmapper.map(savedPost, postDTO.class);
	}

	@Override
	public postDTO updatePost(postDTO Post, int id) {
		post Posta = this.postRepo.findById(id).orElseThrow(() -> new resourceException("user", "id", id));
		Posta.setTitle(Post.getTitle());
		Posta.setContent(Post.getContent());
		Posta.setImageName(Post.getImageName());
		post postd = this.postRepo.save(Posta);
		return this.modelmapper.map(postd, postDTO.class);
	}

	@Override
	public void deletePost(int id) {
		post Post = this.postRepo.findById(id).orElseThrow(() -> new resourceException("post", "id", id));
		this.postRepo.delete(Post);
		// TODO Auto-generated method stub

	}

	@Override
	public postResponse getAllPosts(int pageSize, int pageNumber, String field,String sortDIR) {
		Sort sort= (sortDIR.equalsIgnoreCase("desc"))? Sort.by(field).descending():Sort.by(field).ascending();
		Pageable page = PageRequest.of(pageNumber, pageSize,sort);
		Page<post> pagePost = this.postRepo.findAll(page);
		List<post> list =pagePost.getContent();
	
		List<postDTO> listdto = list.stream().map(post -> modelmapper.map(post, postDTO.class))
				.collect(Collectors.toList());
		postResponse postresponse = new postResponse();
		
		postresponse.setContent(listdto);
		postresponse.setPageNumber(pagePost.getNumber());
		postresponse.setPageSize(pagePost.getSize());
		postresponse.setTotalElements(pagePost.getTotalPages());
		postresponse.setTotalPage(pagePost.getTotalPages());
		postresponse.setIsLastPage(pagePost.isLast());
		return postresponse;
	}

	@Override
	public List<postDTO> getAllPostsBYID(int userID) {
		Users use = this.userRepo.findById(userID).orElseThrow(() -> new resourceException("user", "id", userID));
		List<post> list = this.postRepo.findbyUser(use.getId());
		List<postDTO> listD = list.stream().filter(post -> post.getUser().getId() == userID)
				.map(post -> modelmapper.map(post, postDTO.class)).collect(Collectors.toList());
		if (listD.isEmpty()) {
			throw new resourceException("user havent posted anything", " following id", userID);

		}
		return listD;
	}

}
