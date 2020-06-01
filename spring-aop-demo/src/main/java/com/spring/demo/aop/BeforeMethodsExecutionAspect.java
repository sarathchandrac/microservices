package com.spring.demo.aop;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class BeforeMethodsExecutionAspect {
	@Before("execution(* com.spring.demo.HelloWorld.*Hello(..))")
	public void preRequisite(JoinPoint joinPoint) {
		System.err.println("JoinPoint :: " + joinPoint.getSignature().toLongString() );
		
	}
	/*
	@AfterReturning(value="")
	public void postExecution() {
		System.out.println("After finally..");
	}
	@Around("execution(* com.spring.demo.HelloWorld.sayHello())")
	public void Around() {
		System.out.println("Around Adivice ...");
	}
	*/
	@AfterThrowing(value = "execution(* com.spring.demo.HelloWorld.*Hello(..))", throwing = "ex")
	public void logException(Throwable ex)	{
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String stackTrace = sw.toString();
		System.out.println("Exception thrown ...." + stackTrace);
	}
	
	@After(value="execution(* com.spring.demo.HelloWorld.*Hello(..))")
	public void methodExecutedSuccessfully() {
		System.err.println("Method executed successfully: ");
		//return object;
	}
	
	@Around(value="execution(* com.spring.demo.HelloWorld.*Hello(..))")
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
		System.out.println("Method :: " + pjp.getSignature().toShortString() + " Executed in - " + (System.currentTimeMillis() - startTime) + " Milli Seconds" );
		return returnObject;
	}

}
