/**
 *
 */
package com.vt.base.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * <h1>字符串到日期转换</h1>
 *
 * @author Tony_Zhang05
 * @version 1.0
 */
public class StringToDateConverter implements Converter<String, Date> {

    /* (non-Javadoc)
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public Date convert(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }

        SimpleDateFormat sdf = null;
        if (source.length() == 10) {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        } else {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        try {
            return sdf.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
