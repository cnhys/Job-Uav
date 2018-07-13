/**
 *
 */
package com.vt.base.template.node;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import com.vt.base.util.StringUtil;

/**
 * <h1>dict to option node</h1>
 *
 * @author Tony_Zhang
 * @version 1.0
 */
public class DictToOptionNode extends AbstractRenderableNode {

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
    public DictToOptionNode(int lineNumber, Expression<?> dictExpression, IDictItemService dictItemService) {
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
    public void render(PebbleTemplateImpl self, Writer writer, EvaluationContext context)
            throws PebbleException, IOException {
        // 0. 获取字典参数
        String dictStr = (String) dictExpression.evaluate(self, context);
        if (StringUtils.isBlank(dictStr)) {
            return;
        }

        String dict = dictStr;
        String select = dict;
        String all = null;
        List<String> keyList = new ArrayList<String>();
        String selectedKey = null;
        // commandStatus,status,全部,[01|02|03]
        if (dictStr.contains(IConst.REGEX_COMMA)) {
            String[] dictArr = dictStr.split(IConst.REGEX_COMMA);
            dict = dictArr[0];
            select = dictArr[1];
            if (dictArr.length >= 3) {
                all = dictArr[2];
                if (dictArr.length >= 4) {
                    String selectedDictCode = dictArr[3].substring(1, dictArr[3].length() - 1);
                    String[] keyArr = selectedDictCode.split("\\|");
                    keyList = Arrays.asList(keyArr);
                    if (dictArr.length == 5) {
                        selectedKey = dictArr[4];
                    }
                }
            }
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

        // 2. 转换为SELECT并输出
        StringBuffer result = new StringBuffer();

        result.append(
                "<select name='" + select + "'>");
        if (StringUtil.isNotBlank(all)) {
            result.append("<option value=''>" + all + "</option>");
        }
        for (DictItem dictItem : items) {
            if (keyList.size() == 0 || keyList.contains(dictItem.getDictItemValue())) {
                result.append("<option value='" + dictItem.getDictItemValue()).append("'")
                        .append(StringUtil.isNotBlank(selectedKey) && selectedKey.equals(dictItem.getDictItemValue()) ? " selected='selected'>" : ">")
                        .append(dictItem.getDictItemName())
                        .append("</option>");
            }
        }
        result.append("</select>");

        writer.write(result.toString());
    }

}
