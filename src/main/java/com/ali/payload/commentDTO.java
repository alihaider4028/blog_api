package com.ali.payload;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class commentDTO {

private int commentID;
private String content;
private UserDTO user;

}
