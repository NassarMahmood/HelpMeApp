package demo.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MeasuringAspect {
	private static Log logger = LogFactory.getLog(LoggerAspect.class);
	
	@Around("@annotation(demo.aop.MeasureMe)")
	public Object measureTime(ProceedingJoinPoint jp) throws Throwable{
		// pre processing
		String methodSignature = jp.getTarget().getClass().getSimpleName() + "."
				+ jp.getSignature().getName() + "()";
		
		long beginTime = System.currentTimeMillis();
		
		try {
			Object rv = jp.proceed();
			// success post processing
			return rv;
		} catch (Throwable e) {
			// failure post processing
			throw e;
		} finally {
			long endTime = System.currentTimeMillis();
			long elapsed = endTime - beginTime;
			logger.trace(methodSignature + " - elapsed time: " + elapsed + " ms");
		}
	}
}
