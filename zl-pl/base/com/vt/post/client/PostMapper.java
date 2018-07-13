package com.vt.post.client;

import java.util.List;
import java.util.Map;

import com.vt.base.mapper.IBaseMapper;
import com.vt.post.model.Post;
import com.vt.post.model.PostExample;
import com.vt.post.vo.PostVO;

public interface PostMapper extends IBaseMapper<Post, PostExample, Integer> {
    /**
     * batch delete posts
     *
     * @param postIdList postIds
     * @return
     */
    public int deletePostsById(List<Integer> postIdList);

    /**
     * get postVo by postId
     *
     * @param postId postId
     * @return
     */
    public PostVO getPostVoById(Integer postId);

    /**
     * 查询岗位ID和名称列表
     *
     * @return
     */
    public List<Map<String, Object>> queryPostIdAndNameList();
}