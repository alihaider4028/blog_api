package com.ali.serviceImplemation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ali.entity.category;
import com.ali.exception.resourceException;
import com.ali.payload.categoryDTO;
import com.ali.repository.catergoryRepository;
import com.ali.services.catService;
@Service
public class categoryServiceImplemention implements catService {
@Autowired
private ModelMapper modelMapper;
@Autowired
private catergoryRepository catRepo;
	
	@Override
	public categoryDTO createCategory(categoryDTO catDTO) {
		// TODO Auto-generated method stub
		category cat =this.DTOTOcategory(catDTO);
		category  savedcat =this.catRepo.save(cat); 
		return this.categoryToDTO(savedcat) ;
	}

	@Override
	public categoryDTO updateCategory(categoryDTO catDTO, int id) {
		// TODO Auto-generated method stub
		category cat = this.catRepo.findById(id).orElseThrow(()-> new resourceException("name","id",id));
		this.catRepo.save(cat);
		return this.categoryToDTO(cat);
	}

	@Override
	public void deleteCategory(int id) {
		category cat = this.catRepo.findById(id).orElseThrow(()-> new resourceException("name","id",id));
	this.catRepo.delete(cat);
	}

	@Override
	public List<categoryDTO> getALL() {
		// TODO Auto-generated method stub
		List<category>  list= this.catRepo.findAll();
		List<categoryDTO> catList = list.stream().map(cat->categoryToDTO(cat)).collect(Collectors.toList());
		return catList;
	}

	@Override
	public categoryDTO getbyID(int id) {
		category cat = this.catRepo.findById(id).orElseThrow(()-> new resourceException("name","id",id));
		
		return this.categoryToDTO(cat);
	}
private category DTOTOcategory(categoryDTO catDto) {
	category cat = modelMapper.map(catDto, category.class);
	return cat;
	
}
	
private categoryDTO categoryToDTO(category cat) {
	categoryDTO catDTO = modelMapper.map(cat, categoryDTO.class);
	return catDTO;
	
}	
	
	
	
}
