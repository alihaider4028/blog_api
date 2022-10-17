package com.ali.services;

import java.util.List;

import com.ali.payload.commentDTO;

public interface commentService {

commentDTO createComment(int postID, commentDTO comment);
commentDTO updateComment(int commentID);
void deleteComment(int comment);
List<commentDTO> getAllComment(int postID);
}
