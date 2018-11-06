package com.itheima.controller;

import com.itheima.domain.SysLog;
import com.itheima.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MemberSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.dsig.SignatureMethod;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component("logAop")
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogService sysLogService;

    private Date visitTime;//开始访问的时间
    public Class clazz;//访问的类
    private Method method;//访问的方法

    @Before("execution(* com.itheima.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        //获取开始访问的时间
        visitTime=new Date();
        //获取具体要访问的类
        clazz=jp.getTarget().getClass();
//        //获取具体访问的方法的名称
//        String methodName = jp.getSignature().getName();
//        //获取访问的方法的参数
//        Object[] args = jp.getArgs();
//        if (args==null || args.length==0){
//            //获取无参方法
//            method = clazz.getMethod(methodName);
//        }else {
//            //获取有参方法
//            Class[] classArgs=new Class[args.length];
//            for (int i = 0; i < args.length; i++) {
//                classArgs[i]=args[i].getClass();
//            }
//            method=clazz.getMethod(methodName,classArgs);
//        }

        MethodSignature methodSignature = (MethodSignature) jp.getSignature();
        method=methodSignature.getMethod();
    }

    @After("execution(* com.itheima.controller.*.*(..))")
    public void doAfter(){
        if (clazz==SysLogController.class){
            return;
        }
        //获取访问时长
        long time=new Date().getTime()-visitTime.getTime();
        //获取当前访问的url
        String url="";
        if (clazz!=null && method!=null && clazz!=LogAop.class){
            RequestMapping clazzAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (clazzAnnotation!=null){
                String[] clazzValue = clazzAnnotation.value();
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation!=null){
                    String[] methodValue = methodAnnotation.value();
                    url=clazzValue[0]+methodValue[0];
                }
            }
        }
        //获取访问的ip地址
        String ip = request.getRemoteAddr();

        //获取操作者的用户名
        //首先获得SecurityContext对象
        //获得方法一
        //SecurityContext context = SecurityContextHolder.getContext();
        //获得方法二
        SecurityContext context = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();

        //将信息封装到SysLog对象中
        SysLog sysLog=new SysLog();
        sysLog.setVisitTime(visitTime);
        sysLog.setExecutionTime(time);
        sysLog.setIp(ip);
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setMethod("[类名]:"+clazz.getName()+" [方法名]:"+method.getName());

        //将SysLog对象存储到数据库中
        sysLogService.addSysLog(sysLog);
    }
}
