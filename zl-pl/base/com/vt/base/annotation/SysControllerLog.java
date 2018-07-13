package com.vt.base.annotation;

/**
 * @Title: SysControllerLog.java
 * @Package com.infosys.base.annotation
 * @Description: TODO
 * @author july
 * @date Jun 1, 2015 3:59:30 PM
 * @version V1.0
 */
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *自定义注解 拦截Controller
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysControllerLog {
    String desc() default "";

    String operType() default "";

    String tradeName() default "";
}
