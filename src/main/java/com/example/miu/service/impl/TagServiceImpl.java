package com.example.miu.service.impl;

import com.example.miu.mapper.TagMapper;
import com.example.miu.pojo.table.Tag;
import com.example.miu.pojo.table.TagExample;
import com.example.miu.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <Description> TagServiceImpl
 *
 * @author 26802
 * @version 1.0
 * @ClassName TagServiceImpl
 * @taskId
 * @see com.example.miu.service.impl
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public void addTag(Tag tag) {
        tagMapper.insertSelective(tag);

    }

    @Override
    public void updateTag(Tag tag) {
        tagMapper.updateByPrimaryKeySelective(tag);
    }

    @Override
    public List<Tag> listTagByAreaId(Integer id) {
        TagExample tagExample = new TagExample();
        tagExample.createCriteria().andAreaIdEqualTo(id);
        return tagMapper.selectByExample(tagExample);
    }

    @Override
    public List<Tag> listTagByUserId(Integer id) {
        TagExample tagExample = new TagExample();
        tagExample.createCriteria().andUserIdEqualTo(id);
        return tagMapper.selectByExample(tagExample);
    }

}
