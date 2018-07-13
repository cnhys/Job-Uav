package com.vt.post.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vt.base.IServiceDef;
import com.vt.base.ISysManageControllerPathDef;
import com.vt.base.OptResult;
import com.vt.base.PageData;
import com.vt.base.PageRequest;
import com.vt.base.annotation.SysControllerLog;
import com.vt.base.controller.BaseRestController;
import com.vt.org.model.PostRoleRel;
import com.vt.post.PostConst;
import com.vt.post.model.Post;
import com.vt.post.model.PostExample;
import com.vt.post.service.IPostService;

/**
 * <p> Title: post controller </p>
 * <p> Description: </p>
 *
 * @Author:Devin Bai
 * @Time:Jun 3, 20151:34:18 PM
 * @Version:1.00
 * @Record <pre>
 * Version 	Author	Time		describe
 * ----------------------------------------
 * 1.00		Devin	Jun 3, 2015	release
 * ----------------------------------------
 * </pre>
 */
@Controller
public class PostController extends BaseRestController {
    /**
     * post controller
     */
    private static final long serialVersionUID = -5097374022441604082L;
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * Post Service
     */
    @Autowired
    @Qualifier(IServiceDef.POST_MANAGE_SERVICE)
    private IPostService postService;

    /**
     * post manage filter page show
     *
     * @param model model
     * @return post manage filter html path
     */
    @RequestMapping(value = {ISysManageControllerPathDef.POST_MANAGE_FILTER_URL}, method = RequestMethod.GET)
    public String postManageFilterShow(Model model) {
        return ISysManageControllerPathDef.POST_MANAGE_FILTER_PATH;
    }

    /**
     * query post info
     *
     * @param post  Query conditions
     * @param model model
     * @return post infos
     */
    @RequestMapping(value = {ISysManageControllerPathDef.POST_MANAGE_FILTER_API, ISysManageControllerPathDef.POST_MANAGE_POPUP_API}, method = RequestMethod.POST)
    @ResponseBody
    public PageData<Post> postManageFilterQuery(Post post, PageRequest<PostExample> page, Model model) throws Exception {
        PageData<Post> result = null;
        try {
            result = postService.postManageFilterProcess(post, page, model);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * save post info
     *
     * @param post  to save post
     * @param model model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.POST_MANAGE_ADD_API}, method = RequestMethod.POST)
    @ResponseBody
    @SysControllerLog(desc = PostConst.SYS_CTR_LOG_DESC_SAVEPOST)
    public OptResult savePost(Post post, Model model) throws Exception {
        return postService.savePost(post);
    }

    /**
     * update post
     *
     * @param post  to update post info
     * @param model model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.POST_MANAGE_UPDATE_API}, method = RequestMethod.POST)
    @ResponseBody
    @SysControllerLog(desc = PostConst.SYS_CTR_LOG_DESC_UPDATEPOST)
    public OptResult updatePost(Post post, Model model) throws Exception {
        return postService.updatePost(post);
    }

    /**
     * delete post
     *
     * @param post  to delete post
     * @param model model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {ISysManageControllerPathDef.POST_MANAGE_DELETE_API}, method = RequestMethod.POST)
    @ResponseBody
    @SysControllerLog(desc = PostConst.SYS_CTR_LOG_DESC_DELETEPOST)
    public OptResult deletePost(@RequestParam("postId") String postId, Model model) throws Exception {
        if (StringUtils.isBlank(postId)) {
            return new OptResult(false, "systemmanage.post.delete.null");
        }
        Post post = new Post();
        post.setPostId(Integer.parseInt(postId));
        post.setPostState("0");
        return postService.update(post);
    }

    /**
     * judge the post is exist or not
     *
     * @param post  post
     * @param model model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {ISysManageControllerPathDef.POST_METHOD_URL_ISPOSTCODENOTEXIST_API}, method = RequestMethod.POST)
    @ResponseBody
    public String isPostCodeNotExist(Post post, Model model) throws Exception {
        return postService.isPostCodeNotExist(post);
    }

    /**
     * query role resource rel list
     *
     * @param resRel query condition
     * @param model  model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {ISysManageControllerPathDef.POST_METHOD_URL_QUERYPOSTROLEREL_API}, method = RequestMethod.POST)
    @ResponseBody
    public List<PostRoleRel> queryPostRoleRel(PostRoleRel resRel, Model model) throws Exception {
        return postService.queryPostRoleRel(resRel);
    }

    /**
     * save role resource rel
     *
     * @param roleId to save roleId
     * @param resIds to save resIds
     * @param model  model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {ISysManageControllerPathDef.POST_METHOD_URL_SAVEPOSTROLERELS_API}, method = RequestMethod.POST)
    @ResponseBody
    public OptResult savePostRoleRels(@RequestParam("postId") Integer postId, @RequestParam("roleIds") String roleIds, Model model) throws Exception {
        return postService.savePostRoleRels(postId, roleIds);
    }

    /**
     * 查询岗位下拉框信息
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.POST_METHOD_URL_INITPOSTLISTDATA_API}, method = {RequestMethod.POST,
            RequestMethod.GET})
    @ResponseBody
    public List<Map<String, Object>> initPostListData(Model model) {
        List<Map<String, Object>> list = postService.queryPostIdAndNameList();
        return list;
    }
}
