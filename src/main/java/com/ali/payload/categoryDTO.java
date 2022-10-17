package com.ali.payload;

import javax.persistence.Id;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class categoryDTO {
	@Id int categoryId;
	private String categoryTitle;
	private String categorydescription;
		
}
