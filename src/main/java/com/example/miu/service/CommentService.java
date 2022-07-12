package com.example.miu.service;

import com.example.miu.pojo.table.CommentOfTag;
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

    void addComment(CommentOfTag commentOfTag);

    List<CommentOfTag> listCommentByTagId(Integer tagId);

}
