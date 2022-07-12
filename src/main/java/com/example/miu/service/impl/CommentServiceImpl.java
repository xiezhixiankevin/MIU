package com.example.miu.service.impl;

import com.example.miu.mapper.CommentOfTagMapper;
import com.example.miu.pojo.table.CommentOfTag;
import com.example.miu.pojo.table.CommentOfTagExample;
import com.example.miu.service.CommentService;
import com.example.miu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <Description> CommentServiceImpl
 *
 * @author 26802
 * @version 1.0
 * @ClassName CommentServiceImpl
 * @taskId
 * @see com.example.miu.service.impl
 */
@Repository
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentOfTagMapper commentOfTagMapper;

    @Autowired
    private UserService userService;

    @Override
    public void addComment(CommentOfTag commentOfTag) {
        commentOfTag.setRecommentWho(-1);
        commentOfTagMapper.insertSelective(commentOfTag);
    }

    @Override
    public List<CommentOfTag> listCommentByTagId(Integer tagId) {
        CommentOfTagExample commentOfTagExample = new CommentOfTagExample();
        commentOfTagExample.createCriteria().andTagIdEqualTo(tagId);
        List<CommentOfTag> commentList = commentOfTagMapper.selectByExample(commentOfTagExample);
        for (CommentOfTag comment : commentList) {
            comment.setUsername(userService.getUserById(comment.getUserId()).getUsername());
        }
        return commentList;
    }
}
