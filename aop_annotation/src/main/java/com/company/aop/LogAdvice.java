package com.company.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component("log") // 기본값 : logAdvice
@Aspect
public class LogAdvice {
	
	@Before(value="execution(* com.company.aop.Product.getInfo())")
	public void beforeLog() {
		System.out.println("[공통로그] 비지니스 로직 전 수행(beforeLog)");
	}
	
	@After(value="execution(* com.company.aop.Product.getInfo())")
	public void afterLog() {
		System.out.println("[공통로그] 비지니스 로직 후 수행 - 예외와 상관없이 호출(afterLog)");
	}
	
	@AfterThrowing(value="execution(* com.company.aop.Product.getInfo())")
	public void afterThrowLog() {
		System.out.println("[공통로그] 비지니스 로직 후 수행 - 예외 발생 시 호출(afterThrowLog)");
	}
	
	@AfterReturning(value="execution(* com.company.aop.Product.getInfo())")
	public void afterRetrunLog() {
		System.out.println("[공통로그] 비지니스 로직 후 수행 - 정상 작동 시 호출(afterRetrunLog)");
	}
	
	@Around(value="execution(* com.company.aop.Product.getInfo())")
	public void aroundLog(ProceedingJoinPoint pjp) { // ProceedingJoinPoint 객체를 반드시 받아야 함.
		System.out.println("[공통로그] 비지니스 로직 전 수행(aroundLog)");
		try {
			pjp.proceed();	// 원래 수행하려고 하는 메소드 호출
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("[공통로그] 비지니스 로직 후 수행(aroundLog)");
	}
	
}