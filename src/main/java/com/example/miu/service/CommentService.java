package com.example.miu.service;

import com.example.miu.pojo.table.Comment;

import java.util.*;

/**
 * <Description> CommentService
 *
 * @author 26802
 * @version 1.0
 * @ClassName CommentService
 * @taskId
 * @see com.example.miu.service
 */
public interface CommentService {

    void addComment(Comment comment);

    void updateComment(Comment comment);

    List<Comment> listCommentByTagId(Integer tagId);

}
