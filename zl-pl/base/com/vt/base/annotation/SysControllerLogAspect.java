package com.vt.base.annotation;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.vt.base.IServiceDef;
import com.vt.base.model.Logging;
import com.vt.base.service.ISysLogService;

/**
 * 切点类
 *
 * @author july
 * @version V1.0
 * @Title: SysLogAspect.java
 * @Package com.infosys.base.annotation
 * @Description: TODO
 * @date Jun 2, 2015 10:01:51 AM
 */
@Aspect
@Component
public class SysControllerLogAspect {
    //Controller层切点
    @Pointcut("@annotation(com.vt.base.annotation.SysControllerLog)")
    public void controllerAspect() {
    }

    /**
     * 操作前
     */
    private final static String OPERATE_TYPE_BEFORE = "before";
    /**
     * 操作后
     */
    private final static String OPERATE_TYPE_AFTER = "after";
    /**
     * 日志记录服务
     */
    @Autowired
    @Qualifier(IServiceDef.SYSLOG_SERVICE)
    private ISysLogService sysLogService;


    /**
     * Controller操作前记录
     */
    @Before("controllerAspect()")
    public void doControllerBefore(JoinPoint joinPoint) {
        try {
            //获取方法的注解
            SysControllerLog ann = getControllerAnnotation(joinPoint);
            Logging log = bulidLoggingObject(OPERATE_TYPE_BEFORE);
            log.setLogDesc(ann.desc());
            sysLogService.saveLog(log);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Controller操作后记录
     */
    @After("controllerAspect()")
    public void doControllerAfter(JoinPoint joinPoint) {
        try {
            //获取方法的注解
            SysControllerLog ann = getControllerAnnotation(joinPoint);
            Logging log = bulidLoggingObject(OPERATE_TYPE_AFTER);
            log.setLogDesc(ann.desc());
            sysLogService.saveLog(log);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * 组装日志对象
     *
     * @param triggerEvent
     * @return
     */
    public Logging bulidLoggingObject(String triggerEvent) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //HttpSession session = request.getSession();
        //读取session中的用户
        String user = request.getRemoteUser();
        //请求的IP
        String ip = request.getRemoteAddr();
        Logging log = new Logging();
        log.setLogName(user);
        log.setTriggerEvent(triggerEvent);
        log.setIp(ip);
        log.setOperCode(user);
        log.setOperTime(new Date());

        return log;
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws Exception
     */
    public static SysControllerLog getControllerAnnotation(JoinPoint joinPoint) throws Exception {
        SysControllerLog controllerLog = null;
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class<?> targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class<?>[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    controllerLog = method.getAnnotation(SysControllerLog.class);
                    break;
                }
            }
        }
        return controllerLog;
    }
}

