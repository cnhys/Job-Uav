/**
 *
 */
package com.vt.base.template.node;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.extension.NodeVisitor;
import com.mitchellbosecke.pebble.node.AbstractRenderableNode;
import com.mitchellbosecke.pebble.node.expression.Expression;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplateImpl;
import com.vt.IConst;
import com.vt.base.model.DictItem;
import com.vt.base.service.IDictItemService;

/**
 * <p> Title: dict to checkbox </p>
 * <p> Description: </p>
 *
 * @Author:Devin Bai
 * @Time:Jul 3, 201511:43:02 AM
 * @Version:1.00
 * @Record <pre>
 * Version 	Author	Time		describe
 * ----------------------------------------
 * 1.00		Devin	Jul 3, 2015	release
 * ----------------------------------------
 * </pre>
 */
public class DictToCheckBoxNode extends AbstractRenderableNode {

    /**
     * expression
     */
    private final Expression<?> dictExpression;
    /**
     * dict item service
     */
    private IDictItemService dictItemService;

    /**
     * construction
     *
     * @param lineNumber
     * @param dictExpression
     * @param dictItemService
     */
    public DictToCheckBoxNode(int lineNumber, Expression<?> dictExpression,
                              IDictItemService dictItemService) {
        super(lineNumber);
        this.dictExpression = dictExpression;
        this.dictItemService = dictItemService;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mitchellbosecke.pebble.node.AbstractRenderableNode#accept(com.
     * mitchellbosecke.pebble.extension.NodeVisitor)
     */
    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mitchellbosecke.pebble.node.AbstractRenderableNode#render(com.
     * mitchellbosecke.pebble.template.PebbleTemplateImpl, java.io.Writer,
     * com.mitchellbosecke.pebble.template.EvaluationContext)
     */
    @Override
    public void render(PebbleTemplateImpl self, Writer writer,
                       EvaluationContext context) throws PebbleException, IOException {
        // 0. 获取字典参数
        String dictStr = (String) dictExpression.evaluate(self, context);
        if (StringUtils.isBlank(dictStr)) {
            return;
        }

        String dict = dictStr;
        String checkbox = dict;
        String showItems = null;
        String selected = null;
        List<String> keyList = new ArrayList<String>();
        if (dictStr.contains(IConst.REGEX_COMMA)) {
            String[] dictArr = dictStr.split(IConst.REGEX_COMMA);
            dict = dictArr[0];
            checkbox = dictArr[1];
            if (dictArr.length > 2) {
                showItems = dictArr[2];
                if (StringUtils.isNotBlank(showItems)) {
                    String selectedDictCode = showItems.substring(1, showItems.length() - 1);
                    String[] keyArr = selectedDictCode.split("\\|");
                    keyList = Arrays.asList(keyArr);
                }
                if (dictArr.length > 3) {
                    selected = dictArr[3];
                }
            }
        }

        List<String> selectedList = new ArrayList<String>();
        if (selected != null) {
            selectedList = Arrays.asList(selected.split(IConst.REGEX_VERTICAL_LINE));
        }

        // 1. 获取字典项
        List<DictItem> items = null;

        Object data = context.get(dict);
        if (data != null && data instanceof List) {
            List<?> _items = (List<?>) data;
            if (_items != null && _items.size() > 0) {
                items = new ArrayList<DictItem>();
                for (Object item : _items) {
                    if (item instanceof DictItem) {
                        items.add((DictItem) item);
                    }
                }
            }
        } else {
            // 1.2 从字典中加载
            items = dictItemService.queryDictItemsByDictTypeCode(dict);
        }

        if (items == null || items.size() == 0) {
            return;
        }

        // 2. 转换为checkbox并输出
        StringBuffer result = new StringBuffer();
        result.append("<ul>");
        for (DictItem dictItem : items) {
            if (keyList.size() == 0 || keyList.contains(dictItem.getDictItemValue())) {
                boolean isChecked = selectedList.contains(dictItem.getDictItemValue());
                String checkedCl = isChecked ? "checked='checked'" : "";
                String dictItemId = UUID.randomUUID().toString();
                result.append("<li><input type='checkbox' id='" + dictItemId + "' name='" + checkbox + "' value='" + dictItem.getDictItemValue() + "' " + checkedCl + "/>");
                result.append("<label for='" + dictItemId + "'>" + dictItem.getDictItemName() + "</label></li>");
            }
        }
        result.append("</ul>");
        writer.write(result.toString());
    }

}
