package com.ali.entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name="posts")
public class post {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int postID;	
@Column(name ="title", length = 1000, nullable = false)
private String title;
private String content;
private String imageName;
private Date addedDate;
@ManyToOne
@JoinColumn(name ="categoryId")
private category category;
@ManyToOne
@JoinColumn(name="user_id")
private Users user;
@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
private Set<comment> comments = new HashSet<>();

}
