package com.vt.post.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.vt.base.IServiceDef;
import com.vt.base.OptResult;
import com.vt.base.PageData;
import com.vt.base.PageRequest;
import com.vt.base.exception.BizException;
import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.base.service.IDBUtilService;
import com.vt.base.util.ListUtil;
import com.vt.org.client.PostRoleRelMapper;
import com.vt.org.model.PostRoleRel;
import com.vt.org.model.PostRoleRelExample;
import com.vt.post.client.PostMapper;
import com.vt.post.model.Post;
import com.vt.post.model.PostExample;
import com.vt.post.service.IPostService;
import com.vt.post.vo.PostVO;

/**
 * <p> Title: post service impl </p>
 * <p> Description: </p>
 *
 * @Author:Devin Bai
 * @Time:Jun 3, 20151:47:06 PM
 * @Version:1.00
 * @Record <pre>
 * Version 	Author	Time		describe
 * ----------------------------------------
 * 1.00		Devin	Jun 3, 2015	release
 * ----------------------------------------
 * </pre>
 */
@Service(IServiceDef.POST_MANAGE_SERVICE)
public class PostServiceImpl extends BaseService<Post, PostExample, Integer> implements IPostService {
    /**
     * post service impl
     */
    private static final long serialVersionUID = 7311423974655604721L;
    /**
     * db util service
     */
    @Autowired
    @Qualifier(IServiceDef.DBUTIL_SERVICE)
    private IDBUtilService dbUtilService;
    /**
     * sql session
     */
    @Autowired
    private SqlSession sqlSession;
    /**
     * mapper
     */
    private PostMapper mapper;
    /**
     * postRoleRelMapper
     */
    private PostRoleRelMapper postRoleRelMapper;
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * init
     */
    @PostConstruct
    public void init() {
        super.init();
        mapper = sqlSession.getMapper(PostMapper.class);
        postRoleRelMapper = sqlSession.getMapper(PostRoleRelMapper.class);
        logger.info("Post Service init successfully.");
    }

    /**
     * override,get mapper
     */
    @Override
    public IBaseMapper<Post, PostExample, Integer> getMapper() {
        return mapper;
    }

    /**
     * 查询岗位信息列表
     */
    public PageData<Post> postManageFilterProcess(Post post, PageRequest<PostExample> page, Model model) {
        // 1. package query conditions
        PostExample example = new PostExample();
        if (StringUtils.isNotBlank(post.getPostCode())) {
            example.and().addCriterion("P.POST_CODE like", "%" + post.getPostCode() + "%", "postCode");
        }
        if (StringUtils.isNotBlank(post.getPostName())) {
            example.and().addCriterion("P.POST_NAME like", "%" + post.getPostName() + "%", "postName");
        }
        example.setOrderByClause("POST_ID");
        page.setCondition(example);

        // 2. query
        return query(page);
    }

    /**
     * 新建岗位信息
     */
    public OptResult savePost(Post post) throws BizException {
        // 1. validate
        if (post == null) {
            throw new BizException("systemmanage.post.save.null");
        }

        // 2. build post time info
        post.setCreateTime(new Date());
        post.setLastModfiyTime(new Date());

        // 3. save post
        return create(post);
    }

    /**
     * 修改岗位信息
     */
    public OptResult updatePost(Post post) throws BizException {
        // 1. validate
        if (post == null) {
            throw new BizException("systemmanage.post.update.null");
        }
        if (post.getPostId() == null || post.getPostId() == 0) {
            throw new BizException("systemmanage.post.update.id.null");
        }

        // 2. build post time info


        // 3. update post
        return update(post);
    }

    /**
     * 删除岗位信息
     */
    public OptResult deletePostsById(List<Integer> postIdList) throws BizException {
        // 1. validate
        if (ListUtil.isEmpty(postIdList)) {
            throw new BizException("systemmanage.post.delete.null");
        }
        int count = mapper.deletePostsById(postIdList);
        if (count > 0) {
            return new OptResult(true);
        }
        return new OptResult(false, "database.error");
    }

    /**
     * 判断岗位编码是否已存在
     */
    public String isPostCodeNotExist(Post post) {
        // 1. validate
        if (post == null || StringUtils.isBlank(post.getPostCode())) {
            return Boolean.TRUE.toString();
        }

        // 2. build query conditions
        PostExample example = new PostExample();
        example.and().andPostCodeEqualTo(post.getPostCode());

        // 3. get the count and judge
        int count = mapper.countByExample(example);
        if (count > 0) {
            return Boolean.FALSE.toString();
        }

        return Boolean.TRUE.toString();
    }

    /**
     * 根据ID查询上级岗位名称、业务机构名称、隶属机构名称
     */
    public PostVO getPostVoById(Integer postId) {
        if (postId == null) {
            return null;
        }
        return mapper.getPostVoById(postId);
    }

    /**
     * 根据岗位ID、角色ID查询岗位角色关联信息列表
     */
    public List<PostRoleRel> queryPostRoleRel(PostRoleRel postRoleRel) {
        // 1. validate
        if (postRoleRel == null) {
            return null;
        }

        // 2. build condition
        PostRoleRelExample example = new PostRoleRelExample();
        if (postRoleRel.getPostId() != null) {
            example.and().andPostIdEqualTo(postRoleRel.getPostId());
        }
        if (postRoleRel.getRoleId() != null) {
            example.and().andRoleIdEqualTo(postRoleRel.getRoleId());
        }

        return postRoleRelMapper.selectByExample(example);
    }

    /**
     * 新增岗位角色关联信息
     */
    public OptResult savePostRoleRels(Integer postId, String roleIds) throws BizException {
        // 1. validate
        if (postId == null || postId == 0 || StringUtils.isBlank(roleIds)) {
            return new OptResult(false, "param.null");
        }

        // 2. delete all old data
        PostRoleRelExample example = new PostRoleRelExample();
        example.and().andPostIdEqualTo(postId);
        postRoleRelMapper.deleteByExample(example);

        // 3. build batch insert list
        List<PostRoleRel> postRoleRelList = new ArrayList<PostRoleRel>();
        String[] roleIdArr = roleIds.split(",");
        for (String roleId : roleIdArr) {
            PostRoleRel postRoleRel = new PostRoleRel();
            postRoleRel.setPostId(postId);
            postRoleRel.setRoleId(Integer.parseInt(roleId));
            postRoleRelList.add(postRoleRel);
        }

        // 4. insert
        int count = postRoleRelMapper.insertPostRoleRelBatch(postRoleRelList);
        if (count > 0) {
            return new OptResult(true);
        }
        return new OptResult(false, "database.error");
    }

    /**
     * 查询岗位代码名称列表
     *
     * @return
     */
    public List<Map<String, Object>> queryPostIdAndNameList() {
        return mapper.queryPostIdAndNameList();
    }
}
