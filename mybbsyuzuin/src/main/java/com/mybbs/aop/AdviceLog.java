package com.mybbs.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/* 보조기능, 관점을 수행할 클래스 */
@Component
@Aspect	//	aop 기능 클래스라는 어노테이션
public class AdviceLog {

	/** com.mybbs.service 패키지안의 IF_BBSservice 클래스의 .*(모든메소드)의 (..)(매개변수가 몇개든 상관 없음) 
	 * 패키지명 > 클래스명 > 메서드명 > 메서드 매개변수 타입과 개수 
	 * 원래는 이 부분이 IF_BBSservice Impl에 들어가 성능을 측정함 그게 불편해서 관점지향 언어를 사용하는것 
	 * before : 클래스 진입시만 체크
	 * after : 클래스 끝나는 시점 (보통 around를 씀) */
	
	@Around("execution(* com.mybbs.Service.IF_BBSservice.*(..))")	//	적용할 메서드
	public Object logTime(ProceedingJoinPoint pjp) throws Throwable{	//	 Proceeding~ 지금 뭘 실행하는지 알게함
		long begin = System.currentTimeMillis();
		Object retVal = pjp.proceed();	//	현재 실행할 메서드를 실행하고
		long end = System.currentTimeMillis();	//	또 시간을잰다
		System.out.println("경과시간 : "+(end-begin));
		return retVal;
	}
}
