package com.vt.base.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Title: SysServiceLog.java
 * @Package com.infosys.base.annotation
 * @Description: TODO
 * @author july
 * @date Jun 1, 2015 3:59:52 PM
 * @version V1.0
 */

/**
 *自定义注解 拦截Service
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysServiceLog {
    String desc() default "";
    //String operType() default "";
    //String tradeName()  default "";
}
