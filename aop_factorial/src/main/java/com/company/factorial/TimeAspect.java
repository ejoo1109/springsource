package com.company.factorial;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeAspect {
	
	@Around(value="execution(* com.company.factorial..*(..))")
	public Object measure(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.nanoTime();
		
		try {
			// factorial 결과를 리턴하기 위해 필요함.
			Object obj = pjp.proceed();
			return obj;
			
		} finally {
			long end = System.nanoTime();
			System.out.println("걸릴시간 : " + (end-start));
		}
	}
}