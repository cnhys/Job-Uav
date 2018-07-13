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
import com.vt.base.model.DictItem;
import com.vt.base.service.IDictItemService;

/**
 * <p> Title: jQuery Easy UI Combo box Data </p>
 * <p> Description:
 * { "id": '001', "text": "市场总户" },
 * { "id": '002', "text": "X人民币台账" },
 * { "id": '003', "text": "X人民币代付额度提取台账" },
 * { "id": '004', "text": "X黄金代付额度提取台账" },
 * { "id": '005', "text": "X会员黄金代收台账" },
 * { "id": '101', "text": "会员人民币台账" },
 * { "id": '102', "text": "会员人民币代付台账" },
 * { "id": '103', "text": "会员黄金代付台账" },
 * { "id": '104', "text": "会员黄金代收台账" },
 * { "id": '105', "text": "会员保证金台账" },
 * { "id": '106', "text": "人民币代付额度提取台账" },
 * { "id": '107', "text": "黄金代付额度提取台账" },
 * { "id": '108', "text": "会员黄金库存台账" }
 * </p>
 *
 * @Author Will
 * @Time Aug 4, 2015 - 10:28:26 AM
 * @Version 1.00
 * @Record <pre>
 * Version 	Author	Time		describe
 * ----------------------------------------
 * 1.00		Will	Aug 4, 2015	release
 * ----------------------------------------
 * </pre>
 */
public class DictToComboboxDataNode extends AbstractRenderableNode {
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
    public DictToComboboxDataNode(int lineNumber, Expression<?> dictExpression, IDictItemService dictItemService) {
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
        }

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
