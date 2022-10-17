package com.ali.payload;


import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.ali.entity.Roles;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
private int id;
@NotEmpty
@Email
private String email;
@NotEmpty
@Size(min = 3,message = "enter atleast 3 charachters")
private String name;
private String about;
@NotEmpty
@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  
private String password;
private Set<Roles> roles = new HashSet<>();

}
