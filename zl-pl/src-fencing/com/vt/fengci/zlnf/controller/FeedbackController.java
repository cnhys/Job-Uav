package com.vt.fengci.zlnf.controller;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vt.base.OptResult;
import com.vt.base.controller.BaseGatewayController;
import com.vt.base.util.Uuid32;
import com.vt.fencing.model.ZlnfAnnex;
import com.vt.fencing.model.ZlnfFeedback;
import com.vt.fencing.request.ZlnfFeedbackRequest;
import com.vt.fengci.zlnf.service.IAnnexService;
import com.vt.fengci.zlnf.service.IFeedbackService;

/**
 * user related functions
 * Created by john on 17/7/17.
 */
@Controller
public class FeedbackController extends BaseGatewayController {

	private static final long serialVersionUID = 9038200751680394821L;

	/**
     * member service
     */
	@Autowired
	private IFeedbackService feedbackService;
	
    @Autowired
    private IAnnexService annexService;
    
    
    /**
     * 添加意见反馈
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/feedback/create"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult feedbackCreate(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfFeedbackRequest zlnfFeedbackRequest  = convert(data, ZlnfFeedbackRequest.class);
        // 2. 业务检查
        if (zlnfFeedbackRequest == null) {
            reject("user.activate.model.convert.error");
        }
        Date date = new Date();
        String feedcode = Uuid32.getUUID32();
        String annexcode = Uuid32.getUUID32();
        String username = zlnfFeedbackRequest.getUsername();
        String detail = zlnfFeedbackRequest.getDetail();
        ZlnfFeedback zlnfFeedback = new ZlnfFeedback();
        zlnfFeedback.setFeedcode(feedcode);
        zlnfFeedback.setUsername(username);
        zlnfFeedback.setUsercode(zlnfFeedbackRequest.getUsercode());
        zlnfFeedback.setType(zlnfFeedbackRequest.getType());
        zlnfFeedback.setDetail(detail);
        zlnfFeedback.setCreator(username);
        zlnfFeedback.setCreatedonutc(date);
        OptResult result = OptResult.success();
        try {
        	feedbackService.create(zlnfFeedback);
        	//添加意见反馈图片信息
    		String path = zlnfFeedbackRequest.getAnnexpath();
    		String annexname = zlnfFeedbackRequest.getAnnexname();
    		String httpPath = zlnfFeedbackRequest.getHttpPath();
    		if(StringUtils.isNotBlank(path)){
        		String[] paths = path.split(",");
        		String[] annexnames = annexname.split(",");
        		String[] httpPaths = httpPath.split(",");
        		for(int i = 0;i<paths.length;i++){
        			ZlnfAnnex zlnfAnnex = new ZlnfAnnex();
        			zlnfAnnex.setDeptcode(feedcode);
        			zlnfAnnex.setAnnexcode(annexcode);
        			zlnfAnnex.setAnnexname(annexnames[i]);
        			zlnfAnnex.setAnnexpath(paths[i]);
        			zlnfAnnex.setHttppath(httpPaths[i]);
        			zlnfAnnex.setDepttype("2");
        			zlnfAnnex.setCreator(username);
        			zlnfAnnex.setCreatedonutc(date);
        			annexService.create(zlnfAnnex);
        		}
        	}
        	result.setSuccess(true);
        	result.setReturnCode("0000");
        	result.setMessage("添加反馈意见成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
			result.setReturnCode("1111");
			result.setMessage("添加反馈意见失败");
		}
        return result;
    }
    

}
