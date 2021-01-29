package com.me.community.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/25 19:24
 */
@Mapper
public interface TagMapper {
    /**
     * 保存问题的标签
     * @param qId 问题的id
     * @param tag 标签的名称
     */
    void save(int qId,String tag);

    /**
     * 根据问题id查找相应的标签
     * @param id 问题的id
     * @return 问题的标签列表
     */
    List<String> findTagById(@Param("id") int id);
}
