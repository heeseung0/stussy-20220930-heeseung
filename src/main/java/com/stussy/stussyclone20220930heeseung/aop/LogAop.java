package com.stussy.stussyclone20220930heeseung.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;

@Slf4j
@Aspect
@Component
public class LogAop {

    @Pointcut("execution(* com.stussy.stussyclone20220930heeseung.api.*Api.*(..))")
    private void pointCut() {
    }

    @Pointcut("@annotation(com.stussy.stussyclone20220930heeseung.aop.annotation.LogAspect))")
    private void annotationPointCut() {

    }

    @Around("annotationPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();

        String className = codeSignature.getDeclaringTypeName();
        String methodName = codeSignature.getName();
        String[] paramNames = codeSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();

        for (int i = 0; i < paramNames.length; i++) {
            if (paramNames[i].equals("bindingResult")) {
                BeanPropertyBindingResult bindingResult = null;

                for (Object arg : args) {
                    if (arg.getClass() == BeanPropertyBindingResult.class) {
                        bindingResult = (BeanPropertyBindingResult) arg;
                        break;
                    }
                }

                if (!bindingResult.hasErrors())
                    continue;
            }
            log.info("<<<< Param info >>>> {}.{} >>> [{}: {}]", className, methodName, paramNames[i], args[i]);
        }

        Object result = joinPoint.proceed();

        log.info("<<<< Return >>>> {}.{} >>> [{}]", className, methodName, result);

        return result;
    }
}
