package com.ali.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="comment")
@NoArgsConstructor
@Getter
@Setter
public class comment {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="comment_Id")
private int commentID;
private String content;
@ManyToOne()
@JoinColumn(name="postID")
private post post;
@ManyToOne
@JoinColumn(name ="id")
private Users user;
}
