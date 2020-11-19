package com.alibaba.web.common.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: xiaoxh
 * @Date: Created in 2020/11/13 16:57
 * @Description：
 * @Version: 0.0.1
 **/
@Aspect
@Component
public class WebLogAspect {

    private static Logger log = LoggerFactory.getLogger(WebLogAspect.class);

    private final ObjectMapper mapper;

    @Autowired
    public WebLogAspect(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        for (Object object : joinPoint.getArgs()) {
            if (
                    object instanceof MultipartFile
                            || object instanceof HttpServletRequest
                            || object instanceof HttpServletResponse
            ) {
                continue;
            }
            try {
                if (log.isInfoEnabled()) {
                    log.info(
                            joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName()
//                                    + " : request parameter : " + mapper.writeValueAsString(object)
                    );
                }
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @AfterReturning(returning = "response", pointcut = "webLog()")
    public void doAfterReturning(Object response) throws Throwable {
        if (response != null) {
//            log.info("response parameter : " + mapper.writeValueAsString(response));
        }
    }

}


