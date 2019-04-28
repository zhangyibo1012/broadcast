package cn.orgtec.hix.broadcast.dto;

import cn.hutool.core.date.TimeInterval;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * <p>LogAspectService.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/04/01 <p>
 * <p>@remark: </p>
 */
@Aspect
@Component
@Slf4j
public class LogAspectService {

    /**
     * TimeInterval为Hutool实现的一个计时器
     */
    private TimeInterval interval = new TimeInterval();

    /**
     * 申明一个切点 里面是 execution表达式
     */
    @Pointcut("execution(public * cn.orgtec.hix.broadcast.service.*.*(..))")
    private void controllerAspect() {
    }

    /**
     * 请求method前打印内容
     * @param joinPoint 切面信息
     */
    @Before(value = "controllerAspect()")
    public void methodBefore(JoinPoint joinPoint) {
        interval.start();
//        ServletRequestAttributes requestAttributes;
//        requestAttributes = (ServletRequestAttributes) RequestContextHolder
//                .getRequestAttributes();
//        assert requestAttributes != null;
//        HttpServletRequest request = requestAttributes.getRequest();
//        log.info("===============请求内容===============");
//        try {
//            log.info("请求地址:{}" ,request.getRequestURL().toString());
//            log.info("请求方式:{}" ,request.getMethod());
//            log.info("请求类方法:{}" , joinPoint.getSignature());
//            log.info("请求类方法参数:{}" ,Arrays.toString(joinPoint.getArgs()));
//        } catch (Exception e) {
//            log.error("###LogAspectService.class methodBefore() ### ERROR:", e);
//        }
//        log.info("===============请求内容===============");
    }

    /**
     * 在方法执行完结后打印返回内容
     *
     * @param target  object
     */
    @AfterReturning(returning = "target", pointcut = "controllerAspect()")
    public void methodAfterReturn(Object target) {
        try {
            log.info("Response 响应耗时:{} ms" ,interval.intervalMs());
//            log.info("Response内容:{}" ,JSONUtil.toJsonStr(target));
        } catch (Exception e) {
            log.error("###LogAspectService.class methodAfterReturn() ### ERROR:", e);
        }
    }
}
