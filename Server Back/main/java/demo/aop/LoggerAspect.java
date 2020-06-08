package demo.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LoggerAspect {
	private static Log logger = LogFactory.getLog(LoggerAspect.class);
	
	@Before("@annotation(demo.aop.LogMe)")
	public void printStarsAdvice(JoinPoint jp) {
		LogMe logMe = ((MethodSignature)jp.getSignature()).getMethod().getAnnotation(LogMe.class);
		if (!logMe.printOnlyLogMessages() && (logger.isDebugEnabled() || logger.isTraceEnabled())) {
			System.err.println("**********");
		}
	}

	@Before("@annotation(demo.aop.LogMe)")
	public void printLogDataAdvice(JoinPoint jp) {
		Class<?> targetClass = jp.getTarget().getClass();
		String targetClassName = targetClass.getName();
		
		String methodName = jp.getSignature().getName();
		
		
		logger.debug("********** " + targetClassName + "." + methodName + "()");
	}
}
