package com.ali.services;


import java.util.List;

import com.ali.payload.categoryDTO;

public interface catService {

	//post
	categoryDTO createCategory(categoryDTO catDTO);
	//put
	categoryDTO updateCategory(categoryDTO catDTO, int id);
	//delete
	void deleteCategory(int id);
	//get
	List<categoryDTO> getALL();
	//getID
	categoryDTO getbyID(int id);
	
}
