package com.vt.base.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
import com.vt.base.model.DictItem;
import com.vt.base.model.DictItemExample;
import com.vt.base.model.DictItemVO;
import com.vt.base.model.DictType;
import com.vt.base.model.DictTypeExample;
import com.vt.base.service.IDBUtilService;
import com.vt.base.service.IDictItemService;
import com.vt.base.service.IDictRelService;
import com.vt.base.service.IDictService;
import com.vt.base.util.StringUtil;

/**
 * @author july
 * @version V1.0
 * @Title: DictController.java
 * @Package com.vt.base.controller
 * @date May 29, 2015 9:48:25 AM
 */
@Controller
public class DictController extends BaseRestController {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 9036788082560641804L;

    /**
     * 机构管理服务
     */
    @Autowired
    @Qualifier(IServiceDef.DBUTIL_SERVICE)
    private IDBUtilService dbUtilService;

    /**
     * 字典项服务
     */
    @Autowired
    @Qualifier(IServiceDef.DICT_ITEM_SERVICE)
    private IDictItemService dictItemService;

    /**
     * 字典服务
     */
    @Autowired
    @Qualifier(IServiceDef.DICT_TYPE_SERVICE)
    private IDictService dictService;

    @Autowired
    @Qualifier(IServiceDef.DICT_ITEMREL_SERVICE)
    private IDictRelService dictRelService;

    /**
     * <p>
     * Description:显示机构查询页面
     * </p>
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.BASE_DICT_QUERY_CONTROOLE_URL}, method = RequestMethod.GET)
    public String showDict(Model model) {
        return ISysManageControllerPathDef.BASE_DICT_FILTER_PATH;
    }

    /**
     * <p>
     * Description:字典列表查询
     * </p>
     *
     * @param data
     * @param model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.BASE_DICT_QUERY_CONTROOLE_API}, method = RequestMethod.POST)
    @ResponseBody
    public PageData<DictItem> processFormData(DictItem dictItem, PageRequest<DictItemExample> page, Model model) {
        PageData<DictItem> result = null;
        // 封装查询条件
        DictItemExample example = new DictItemExample();
        DictItemExample.Criteria dictItemCri = example.createCriteria();
        if (StringUtils.isNotBlank(dictItem.getDictTypeCode())) {
            dictItemCri.andDictTypeCodeLike(StringUtil.dbQueryLike(dictItem.getDictTypeCode()));
        }
        if (StringUtils.isNotBlank(dictItem.getDictItemName())) {
            dictItemCri.andDictItemNameLike(StringUtil.dbQueryLike(dictItem.getDictItemName()));
        }
        page.setCondition(example);
        result = dictItemService.query(page);
        return result;
    }

    /**
     * <p>
     * Description:查询全部字典项
     * </p>
     *
     * @param data
     * @param model
     * @return
     */
    @RequestMapping(value = {
            ISysManageControllerPathDef.BASE_DICT_QUERYALLPAGE_CONTROOLE_API}, method = RequestMethod.POST)
    @ResponseBody
    public List<DictItem> processFormAllData(DictItem dictItem, PageRequest<DictItemExample> page, Model model) {
        List<DictItem> result = null;
        // 封装查询条件
        DictItemExample example = new DictItemExample();
        result = dictItemService.getResult(example);
        return result;
    }

    /**
     * 获取子集字典
     *
     * @param dictItem
     * @param model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.BASE_DICT_QUERY_SUB_ITEMS_API}, method = RequestMethod.POST)
    @ResponseBody
    public OptResult querySubItems(DictItem dictItem, Model model) {
        List<DictItem> items = new ArrayList<DictItem>();
        OptResult optResult = OptResult.success();
        if (StringUtils.isNotBlank(dictItem.getDictTypeCode())) {
            if (StringUtils.isNotBlank(dictItem.getDictItemValue())) {
                items = dictItemService.querySubDictItemsByDictTypeCodeAndItemValue(dictItem.getDictTypeCode(),
                        dictItem.getDictItemValue());
            } else {
                items = dictItemService.queryDictItemsByDictTypeCode(dictItem.getDictTypeCode());
            }

            List<DictItemVO> list = new ArrayList<DictItemVO>();
            for (DictItem item : items) {
                DictItemVO dictItemVO = new DictItemVO();
                dictItemVO.setId(item.getDictItemValue());
                dictItemVO.setText(item.getDictItemName());
                list.add(dictItemVO);
            }
            optResult.setData(list);
        }
        return optResult;
    }

    /**
     * update DictType
     *
     * @param post  to update dictType info
     * @param model model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.BASE_DICT_UPDATE_DICTTYPE_API}, method = RequestMethod.POST)
    @ResponseBody
    public OptResult updateDictItem(DictItem dictItem, DictType dictType, Model model) throws Exception {
        OptResult result = new OptResult();
        dictItemService.updateByItemTypeCodeAndVale(dictItem);
        dictService.updateDictTypeByCode(dictType);
        result.setSuccess(true);
        return result;
    }

    /**
     * add DictType
     *
     * @param post  to update dictType info
     * @param model model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.BASE_DICT_ADD_DICTTYPE_API}, method = RequestMethod.POST)
    @ResponseBody
    public OptResult addDictType(DictType dictType, DictItem dictItem, Model model) throws Exception {
        OptResult result = null;
        if (dictType != null && dictType.getDictTypeCode() != null && !"".equals(dictType.getDictTypeCode())) {
            DictTypeExample dictTypeExam = new DictTypeExample();
            DictTypeExample.Criteria dictCri = dictTypeExam.createCriteria();
            dictCri.andDictTypeCodeEqualTo(dictType.getDictTypeCode());
            int count = dictService.getResultCount(dictTypeExam);
            if (count < 1) {
                dictService.create(dictType);
            }
        }
        if (dictItem != null) {
            DictItemExample dictItemExam = new DictItemExample();
            DictItemExample.Criteria itemCri = dictItemExam.createCriteria();
            itemCri.andDictTypeCodeEqualTo(dictItem.getDictTypeCode());
            itemCri.andDictItemValueEqualTo(dictItem.getDictItemValue());
            int count = dictItemService.getResultCount(dictItemExam);
            if (count < 1) {
                result = dictItemService.create(dictItem);
            } else {
                result = new OptResult(false);
            }
        }
        return result;
    }

    /**
     * add DictItem
     *
     * @param post  to update dictItem info
     * @param model model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.BASE_DICTITEM_ADD_DICT_API}, method = RequestMethod.POST)
    @ResponseBody
    public OptResult addDictItem(DictItem dictItem, Model model) throws Exception {
        OptResult result = dictItemService.create(dictItem);
        return result;
    }

    /**
     * query DictItem
     *
     * @param post  to update dictItem info
     * @param model model
     * @return
     */
    @RequestMapping(value = {
            ISysManageControllerPathDef.BASE_DICTITEM_QUERY_CONTROOLE_API}, method = RequestMethod.POST)
    @ResponseBody
    public List<DictItem> queryDictItem(@RequestParam("typeId") String typeId, Model model) throws Exception {
        List<DictItem> retList = null;
        if (typeId != null && !"".equals(typeId)) {
            DictType dictType = dictService.getById(Integer.parseInt(typeId));
            if (dictType == null) {
                return null;
            }
            DictItemExample dictItemExam = new DictItemExample();
            dictItemExam.createCriteria().andDictTypeCodeEqualTo(dictType.getDictTypeCode());
            retList = dictItemService.getResult(dictItemExam);
        }
        return retList;
    }

    /**
     * 查询关联的条目主键，已逗号分隔
     *
     * @param iId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {ISysManageControllerPathDef.BASE_DICTITEM_QUERYREL_CONTROOLE_API}, method = RequestMethod.POST)
    @ResponseBody
    public OptResult queryDictItemIdRes(@RequestParam("iId") String iId, Model model) throws Exception {
        OptResult optResult = OptResult.success();
        String res = dictRelService.queryDictRelIdByItemId(Integer.parseInt(iId));
        optResult.setData(res);
        return optResult;
    }

    /**
     * 增加条目关联
     *
     * @param iId
     * @param itemLike
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {ISysManageControllerPathDef.BASE_DICT_ADD_DICTITEMREL_API}, method = RequestMethod.POST)
    @ResponseBody
    public OptResult insertDictItemIdRes(@RequestParam("iId") String iId, @RequestParam("itemLike") String itemLike,
                                         Model model) throws Exception {
        return dictRelService.insertDictItemIdRes(iId, itemLike);
    }

    /**
     * 根据字典typeCode查询字典项
     *
     * @param typeCode
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {
            ISysManageControllerPathDef.BASE_DICTITEM_QUERY_BY_TYPECODE_CONTROOLE_API}, method = RequestMethod.POST)
    @ResponseBody
    public List<DictItem> dictItemQueryByTypeCode(@RequestParam("typeCode") String typeCode, Model model) throws Exception {
        List<DictItem> retList = null;
        if (typeCode != null && !"".equals(typeCode)) {
            DictItemExample dictItemExam = new DictItemExample();
            dictItemExam.createCriteria().andDictTypeCodeEqualTo(typeCode);
            retList = dictItemService.getResult(dictItemExam);
        }
        return retList;
    }
}
