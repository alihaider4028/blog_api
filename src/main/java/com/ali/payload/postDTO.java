package com.ali.payload;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
@AllArgsConstructor
public class postDTO {
	private int postID;	
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	private categoryDTO category;
	private UserDTO user;
	private List<commentDTO> comments = new ArrayList<>();
}


