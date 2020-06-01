package com.training.mobile.aop;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class BeforeMethodsExecutionAspect {
	@Pointcut(value="execution(* com.training.mobile..*.*(..))")
	public void allMobileServices() {};
	

	@Around(value="allMobileServices()")
	public Object logExecutionTime(ProceedingJoinPoint pjp) {
		//@Before
		long startTime = System.currentTimeMillis();
		Object returnObject = null;
		try {
			returnObject = pjp.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		// @After
		System.err.println("Method :: " + pjp.getSignature().toShortString() + " Executed in - " + (System.currentTimeMillis() - startTime) + " Milli Seconds" );
		return returnObject;
	}
	@AfterThrowing(value = "allMobileServices()", throwing = "ex")
	public void logException(Throwable ex)	{
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String stackTrace = sw.toString();
		System.err.println("Exception thrown ...." + stackTrace);
	}
	
	/*
	@Before("allMobileServices()")
	public void preRequisite(JoinPoint joinPoint) {
		System.err.println("JoinPoint :: " + joinPoint.getSignature().toLongString() );
		
	}
	
	@AfterReturning(value="")
	public void postExecution() {
		System.out.println("After finally..");
	}
	@Around("execution(* com.spring.demo.HelloWorld.sayHello())")
	public void Around() {
		System.out.println("Around Adivice ...");
	}
	*/

	/*
	@After(value="allMobileServices()")
	public void methodExecutedSuccessfully() {
		System.err.println("Method executed successfully: ");
		//return object;
	}
	*/
	

}
