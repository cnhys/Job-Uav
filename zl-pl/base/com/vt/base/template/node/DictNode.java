/**
 *
 */
package com.vt.base.template.node;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;

import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.extension.NodeVisitor;
import com.mitchellbosecke.pebble.node.AbstractRenderableNode;
import com.mitchellbosecke.pebble.node.expression.Expression;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplateImpl;
import com.vt.IConst;
import com.vt.base.model.DictItem;
import com.vt.base.model.DictItemExample;
import com.vt.base.service.IDictItemService;

/**
 * <h1>Dict Node</h1>
 *
 * @author Tony_Zhang05
 * @version 1.0
 */
public class DictNode extends AbstractRenderableNode {
    /**
     * expression
     */
    private final Expression<?> dictExpression;
    /**
     * dict item service
     */
    private IDictItemService dictItemService;

    /**
     * 默认构造
     *
     * @param lineNumber
     * @param dictExpression
     */
    public DictNode(int lineNumber, Expression<?> dictExpression, IDictItemService dictItemService) {
        super(lineNumber);
        this.dictExpression = dictExpression;
        this.dictItemService = dictItemService;
    }

    /* (non-Javadoc)
     * @see com.mitchellbosecke.pebble.node.AbstractRenderableNode#accept(com.mitchellbosecke.pebble.extension.NodeVisitor)
     */
    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }

    /* (non-Javadoc)
     * @see com.mitchellbosecke.pebble.node.AbstractRenderableNode#render(com.mitchellbosecke.pebble.template.PebbleTemplateImpl, java.io.Writer, com.mitchellbosecke.pebble.template.EvaluationContext)
     */
    @Override
    public void render(PebbleTemplateImpl self, Writer writer,
                       EvaluationContext context) throws PebbleException, IOException {
        // 0. 获取字典参数
        String dict = (String) dictExpression.evaluate(self, context);

        if (StringUtils.isBlank(dict)) {
            return;
        } else if (IConst.DICT_ALL.equals(dict)) {
            List<DictItem> items = dictItemService.getResult(new DictItemExample());

            if (items == null || items.size() == 0) {
                return;
            }

            Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();
            for (DictItem dictItem : items) {
                if (data.containsKey(dictItem.getDictTypeCode())) {
                    ((Map<String, String>) data.get(dictItem.getDictTypeCode())).put(dictItem.getDictItemValue(), dictItem.getDictItemName());
                } else {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put(dictItem.getDictItemValue(), dictItem.getDictItemName());
                    data.put(dictItem.getDictTypeCode(), map);
                }
            }

            // 2. 转换为JSON并输出
            ObjectMapper mapper = new ObjectMapper();
            StringWriter sw = new StringWriter();
            mapper.writeValue(sw, data);

            String result = "var dict = " + sw.toString();
            writer.write(result);
        } else {
            // 1. 获取字典项
            List<DictItem> items = dictItemService.queryDictItemsByDictTypeCode(dict);

            if (items == null || items.size() == 0) {
                return;
            }

            Map<String, String> data = new HashMap<String, String>();
            for (DictItem dictItem : items) {
                data.put(dictItem.getDictItemValue(), dictItem.getDictItemName());
            }

            // 2. 转换为JSON并输出
            ObjectMapper mapper = new ObjectMapper();
            StringWriter sw = new StringWriter();
            mapper.writeValue(sw, data);

            String result = "var " + dict + " = " + sw.toString();
            writer.write(result);
        }

    }
}
