package com.daniel.iflostfind.util.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {

    //TODO
//    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
//    public void getMappedControllerMethods() {
//
//    }
//
//    @Pointcut("@target(com.daniel.iflostfind.util.annotation.NotAccessibleIfAuthenticated)")
//    public void notAccessibleIfAuthenticated() {
//
//    }
//
//    @Around("notAccessibleIfAuthenticated() && getMappedControllerMethods()")
//    public Object redirectToIndexIfAlreadyAuthenticated(ProceedingJoinPoint pjp) throws Throwable {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (!(auth instanceof AnonymousAuthenticationToken)) {
//            return "redirect:/index";
//        }
//        return pjp.proceed();
//    }
}
