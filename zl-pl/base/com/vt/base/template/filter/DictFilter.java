/**
 *
 */
package com.vt.base.template.filter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.mitchellbosecke.pebble.extension.Filter;
import com.vt.base.model.DictItem;
import com.vt.base.model.DictItemExample;
import com.vt.base.service.IDictItemService;

/**
 * <h1>字典Key过滤器</h1>
 *
 * @author Tony_Zhang05
 * @version 1.0
 */
public class DictFilter implements Filter {
    /**
     * 字典服务
     */
    private IDictItemService dictItemService;

    /* (non-Javadoc)
     * @see com.mitchellbosecke.pebble.extension.NamedArguments#getArgumentNames()
     */
    public List<String> getArgumentNames() {
        return Arrays.asList("dictKey");
    }

    /* (non-Javadoc)
     * @see com.mitchellbosecke.pebble.extension.Filter#apply(java.lang.Object, java.util.Map)
     */
    public Object apply(Object input, Map<String, Object> args) {
        // 0. 字典key
        String dictKey = String.valueOf(args.get("dictKey"));
        if (StringUtils.isBlank(dictKey)) {
            return input;
        }
        // 1. 获取字典参数
        DictItemExample example = new DictItemExample();
        example.createCriteria().andDictItemValueEqualTo(dictKey);

        List<DictItem> items = dictItemService.getResult(example);

        if (items == null || items.size() == 0) {
            return input;
        }

        // 2. 遍历字典
        // TODO 考虑缓存

        String target = String.valueOf(input);

        for (DictItem dictItem : items) {
            String code = dictItem.getDictItemValue();
            if (StringUtils.equals(code, target)) {
                return dictItem.getDictItemName();
            }
        }

        return input;
    }

    /**
     * @param dictItemService the dictItemService to set
     */
    public void setDictItemService(IDictItemService dictItemService) {
        this.dictItemService = dictItemService;
    }
}
