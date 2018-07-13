package com.vt.base.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <h1>Object Compare Utils</h1>
 * <p>
 * use to compare to object, and generate a description for value changes.
 * </p>
 * Created by zhangtao on 15/6/6.
 */
public class ObjectCompareUtils {
    /**
     * compare the given objects
     *
     * @param original
     * @param current
     * @param pairs
     * @return
     */
    public static String compare(Object original, Object current, Map<String, String> pairs) {
        if (original == null || current == null) {
            return StringUtils.EMPTY;
        }
        if (!original.getClass().isAssignableFrom(current.getClass())) {
            return StringUtils.EMPTY;
        }
        Set<String> keys = pairs.keySet();

        StringBuffer sb = new StringBuffer();
        for (String key : keys) {
            String result = compare(key, pairs.get(key), original, current);
            if (StringUtils.isNotBlank(result)) {
                if (sb.length() == 0) {
                    sb.append(result);
                } else {
                    sb.append(" |||| " + result);
                }
            }
        }
        return sb.toString();
    }

    /**
     * compare the given objects
     *
     * @param original
     * @param current
     * @return
     */
    public static String compare(Object original, Object current) {
        if (original == null || current == null) {
            return StringUtils.EMPTY;
        }
        if (!original.getClass().isAssignableFrom(current.getClass())) {
            return StringUtils.EMPTY;
        }
        Class<?> clz = original.getClass();
        Field[] fields = clz.getDeclaredFields();

        if (fields == null || fields.length == 0) {
            return StringUtils.EMPTY;
        }

        StringBuffer sb = new StringBuffer();
        for (Field field : fields) {
            String result = compare(field.getName(), field.getName(), original, current);
            if (StringUtils.isNotBlank(result)) {
                if (sb.length() == 0) {
                    sb.append(result);
                } else {
                    sb.append(" |||| " + result);
                }
            }
        }

        return sb.toString();
    }

    /**
     * compare given field
     *
     * @param fieldName
     * @param original
     * @param current
     * @return
     */
    private static String compare(String fieldName, String fieldDesc, Object original, Object current) {
        String methodName = "get" + StringUtils.upperCase(StringUtils.substring(fieldName, 0, 1)) + StringUtils.substring(fieldName, 1);
        Method origMethod = getDeclaredMethod(original.getClass(), methodName);
        Method curMethod = getDeclaredMethod(current.getClass(), methodName);
        if (origMethod == null || curMethod == null) {
            return StringUtils.EMPTY;
        }
        Object origValue = invoke(origMethod, original);
        Object curValue = invoke(curMethod, current);

        if (origValue == null) {
            origValue = StringUtils.EMPTY;
        }

        if (curValue == null) {
            return StringUtils.EMPTY;
        }

        if (!ObjectUtils.equals(origValue, curValue)) {
            return "{" + fieldDesc + " 原值 [" + origValue.toString() + "], 现值 [" + curValue.toString() + "] }";
        }
        return StringUtils.EMPTY;
    }

    /**
     * invoke method
     *
     * @param method
     * @param target
     * @return
     */
    private static Object invoke(Method method, Object target) {
        try {
            return method.invoke(target);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return StringUtils.EMPTY;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return StringUtils.EMPTY;
        }
    }

    /**
     * obtain given method
     *
     * @param clz
     * @param methodName
     * @return
     */
    private static Method getDeclaredMethod(Class<?> clz, String methodName) {
        try {
            return clz.getDeclaredMethod(methodName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }
}
