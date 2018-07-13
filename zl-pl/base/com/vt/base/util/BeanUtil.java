package com.vt.base.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * <p> Title: Bean工具类 </p>
 * <p> Description: </p>
 *
 * @作者:白松涛 Devin_Bai@infosys.com
 * @创建时间:2014-10-17下午4:16:40
 * @版本:1.00
 * @修改记录 <pre>
 * 版本	修改人	修改时间		描述
 * ----------------------------------------
 * 1.00	白松涛	2014-10-17	初始版本
 * ----------------------------------------
 * </pre>
 */
public class BeanUtil extends BeanUtils {
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(BeanUtil.class);
    /**
     * get方法前缀
     */
    public static final String GET_METHOD_PREFIX = "get";
    /**
     * set方法前缀
     */
    public static final String SET_METHOD_PREFIX = "set";
    /**
     * Set方法位置
     */
    public static final int SET_INDEX = 3;

    /**
     * 给对象赋值
     *
     * @param obj       赋值对象
     * @param fieldName 赋值属性
     * @param value     值
     */
    public static void setValue(Object obj, String fieldName, Object value) {
        Method method = null;
        String methodName = SET_METHOD_PREFIX + StringUtils.capitalize(fieldName);
        Field field;
        try {
            field = obj.getClass().getDeclaredField(fieldName);
            if (field.getGenericType().equals(String.class)) {
                method = obj.getClass().getMethod(methodName, String.class);
            } else if (field.getGenericType().equals(java.util.Date.class)) {
                method = obj.getClass().getMethod(methodName, java.util.Date.class);
            } else if (field.getGenericType().equals(java.math.BigDecimal.class)) {
                method = obj.getClass().getMethod(methodName, java.math.BigDecimal.class);
            } else if (field.getGenericType().equals(Integer.class)) {
                method = obj.getClass().getMethod(methodName, Integer.class);
            } else if (field.getGenericType().equals(Integer.TYPE)) {
                obj = Integer.valueOf(obj.toString());
                method = obj.getClass().getMethod(methodName, int.class);
            } else {
                method = obj.getClass().getMethod(methodName, String.class);
            }
            method.invoke(obj, value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 获取对象值
     *
     * @param obj       获取对象
     * @param fieldName 获取属性名
     * @return
     */
    public static Object getValue(Object obj, String fieldName) {
        String methodName = GET_METHOD_PREFIX + StringUtils.capitalize(fieldName);
        Object valueObj = null;
        Method method = null;
        try {
            method = obj.getClass().getMethod(methodName);
            valueObj = method.invoke(obj, new Object[]{});
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return valueObj;
    }

    /**
     * 当属性值为空时拷贝
     *
     * @param source 拷贝源
     * @param target 拷贝目标
     */
    public static void copyPropertiesWhenNull(Object source, Object target) {
        // 所有方法
        Method[] allMethods = source.getClass().getMethods();
        // 循环
        try {
            for (Method method : allMethods) {
                // 如果是get方法
                if (method.getName().startsWith(GET_METHOD_PREFIX)) {
                    Object sourceValue = method.invoke(source, new Object[]{});
                    Method targetMethod = target.getClass().getMethod(method.getName());
                    Object targetValue = targetMethod.invoke(target, new Object[]{});
                    if (targetValue == null) {
                        setValue(target, sourceValue, "set" + method.getName().substring(SET_INDEX), targetMethod);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 设置值到对应对象
     *
     * @param target
     * @param value
     * @param methodName
     * @param obatainValueMethod 获取值方法
     */
    private static void setValue(Object target, Object value, String methodName, Method obatainValueMethod) {
        Method method = getMethod(target, value, methodName, obatainValueMethod.getReturnType());
        if (method != null) {
            try {
                method.invoke(target, new Object[]{value});
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    /**
     * 获取给定方法
     *
     * @param target
     * @param value
     * @param methodName
     * @param valueClasses 值类型
     * @return
     */
    private static Method getMethod(Object target, Object value, String methodName, Class<?>... valueClasses) {
        Method method = null;
        if (value == null) {
            try {
                return target.getClass().getMethod(methodName, valueClasses);
            } catch (SecurityException e) {
            } catch (NoSuchMethodException e) {
            }
            return null;
        }
        if (value instanceof Integer) {// 如果为Integer, 首先尝试使用int.class
            try {
                return target.getClass().getMethod(methodName, new Class[]{int.class});
            } catch (SecurityException e) {
            } catch (NoSuchMethodException e) {
            }
        } else if (value instanceof Long) {// 如果为Long, 首先尝试使用long.class
            try {
                return target.getClass().getMethod(methodName, new Class[]{long.class});
            } catch (SecurityException e) {
            } catch (NoSuchMethodException e) {
            }
        } else if (value instanceof java.sql.Date) {
            try {
                return target.getClass().getMethod(methodName, new Class[]{java.util.Date.class});
            } catch (SecurityException e) {
            } catch (NoSuchMethodException e) {
            }
        }
        try {
            method = target.getClass().getMethod(methodName,
                    new Class[]{value.getClass()});
        } catch (SecurityException e) {
        } catch (NoSuchMethodException e) {
        }
        return method;
    }

    /**
     * 获取所有属性
     *
     * @param clazz class
     * @return
     */
    public static String[] getDeclaredFields(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<String> fieldList = new ArrayList<String>();
        if (!ArrayUtils.isEmpty(fields)) {
            for (Field field : fields) {
                fieldList.add(field.getName());
            }
        }
        return fieldList.toArray(new String[]{});
    }
}

