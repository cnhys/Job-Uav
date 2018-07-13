package com.vt.base.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vt.base.IServiceDef;
import com.vt.base.ISysManageControllerPathDef;
import com.vt.base.OptResult;
import com.vt.base.PageData;
import com.vt.base.PageRequest;
import com.vt.base.service.ISqlService;
import com.vt.base.util.StringUtil;


/**
 * @author
 * @version V1.0
 * @Title: SqlController.java
 * @Package com.vt.base.controller
 * @Description: TODO
 * @date Jun 3, 2015 3:12:56 PM
 */
@Controller
public class SqlController extends BaseRestController {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -7449850231030829614L;
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * sql语句执行服务
     */
    @Autowired
    @Qualifier(IServiceDef.SQL_SERVICE)
    private ISqlService sqlService;

    /**
     * <p> Description:显示SQL执行页面</p>
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.SQL_QUERY_CONTROLLER_URL}, method = RequestMethod.GET)
    public String showParam(Model model) {
        return ISysManageControllerPathDef.SQL_FILTER_FILE_PATH;
    }

    /**
     * <p> Description:SQL执行</p>
     *
     * @param model
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value = {ISysManageControllerPathDef.SQL_QUERY_CONTROLLER_API}, method = RequestMethod.POST)
    @ResponseBody
    public OptResult processFormData(String sql, PageRequest<String> request, Model model) {
        OptResult opt = new OptResult(true);
        model.addAttribute("sql", sql);
        if (StringUtil.isEmpty(sql)) {
            opt.setSuccess(false);
            opt.setMessage("sql语句不能为空");
            return opt;
        }
        try {
            sql = sql.trim();
            if (sql.toUpperCase().startsWith("SELECT")) {

                request.setCondition(sql);
                PageData<Map> pageData = sqlService.getBySql(request);
                List<Map> resultList = pageData.getData();
                Set keySet = new HashSet();
                for (int i = 0; i < resultList.size(); i++) {
                    Map map = resultList.get(i);
                    if (map != null) {
                        keySet.addAll(map.keySet());//汇总列名，无值的列不能显示
                    }
                }

                List<String> keyList = new ArrayList(keySet);

                List<List> valueList = new ArrayList();
                for (Map kv : resultList) {
                    List rowList = new ArrayList();
                    for (String key : keyList) {
                        Object v = kv.get(key);
                        rowList.add(v);
                    }
                    valueList.add(rowList);
                }
                Map map = new HashMap();
                map.put("currentPage", pageData.getCurrentPage() - 1);//当前页
                map.put("limit", pageData.getLimit());//每页条数
                map.put("total", pageData.getTotal());//总记录数
                map.put("keyList", keyList);
                map.put("valueList", valueList);
                opt.setData(map);

            } else if (sql.toUpperCase().startsWith("INSERT")) {
                sqlService.insertBySql(sql);
            } else if (sql.toUpperCase().startsWith("UPDATE")) {
                sqlService.updateBySql(sql);
            } else if (sql.toUpperCase().startsWith("DELETE")) {
                sqlService.deleteBySql(sql);
            } else {
                //model.addAttribute("msg", "sql格式不正确");
                opt.setSuccess(false);
                opt.setMessage("sql格式不正确");
                return opt;
            }
        } catch (Exception e) {
            opt.setSuccess(false);
            opt.setMessage("sql执行失败。原因：" + e.getMessage() + e.getCause());
            logger.error("sql执行异常", e.getMessage());
            return opt;
        }

        return opt;
    }

}
