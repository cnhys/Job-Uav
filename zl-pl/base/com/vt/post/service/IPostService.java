package com.vt.post.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.vt.base.OptResult;
import com.vt.base.PageData;
import com.vt.base.PageRequest;
import com.vt.base.annotation.SysControllerLog;
import com.vt.base.exception.BizException;
import com.vt.base.service.IBaseService;
import com.vt.org.model.PostRoleRel;
import com.vt.post.PostConst;
import com.vt.post.model.Post;
import com.vt.post.model.PostExample;
import com.vt.post.vo.PostVO;

/**
 * <p> Title: post service </p>
 * <p> Description: </p>
 *
 * @Author:Devin Bai
 * @Time:Jun 3, 20151:38:53 PM
 * @Version:1.00
 * @Record <pre>
 * Version 	Author	Time		describe
 * ----------------------------------------
 * 1.00		Devin	Jun 3, 2015	release
 * ----------------------------------------
 * </pre>
 */
public interface IPostService extends IBaseService<Post, PostExample, Integer> {
    /**
     * query post infos
     *
     * @param post  query condition
     * @param model model
     * @return post info list
     */
    public PageData<Post> postManageFilterProcess(Post post, PageRequest<PostExample> page, Model model);

    /**
     * save post info
     *
     * @param post to save post info
     * @return
     */
    @SysControllerLog(desc = PostConst.SYS_SRV_LOG_DESC_SAVEPOST)
    public OptResult savePost(Post post) throws BizException;

    /**
     * update post
     *
     * @param post to update post
     * @return
     */
    @SysControllerLog(desc = PostConst.SYS_SRV_LOG_DESC_UPDATEPOST)
    public OptResult updatePost(Post post) throws BizException;

    /**
     * delete post
     *
     * @param post to delete post
     * @return
     */
    @SysControllerLog(desc = PostConst.SYS_SRV_LOG_DESC_DELETEPOST)
    public OptResult deletePostsById(List<Integer> postIdList) throws BizException;

    /**
     * judge postCode is exist or not
     *
     * @param post post
     * @return ture-not exist;false exist
     */
    public String isPostCodeNotExist(Post post);

    /**
     * get postVo by postId
     *
     * @param postId postId
     * @return
     */
    public PostVO getPostVoById(Integer postId);

    /**
     * query PostRoleRel
     *
     * @param postRoleRel condition
     * @return
     */
    public List<PostRoleRel> queryPostRoleRel(PostRoleRel postRoleRel);

    /**
     * save postRoleRels
     *
     * @param postId
     * @param roleIds
     * @return
     * @throws BizException
     */
    public OptResult savePostRoleRels(Integer postId, String roleIds) throws BizException;

    /**
     * 查询岗位代码名称列表
     *
     * @return
     */
    public List<Map<String, Object>> queryPostIdAndNameList();
}
