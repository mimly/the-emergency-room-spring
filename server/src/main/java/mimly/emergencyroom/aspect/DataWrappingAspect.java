package mimly.emergencyroom.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j(topic = " ** DATA WRAPPING ASPECT **")
public class DataWrappingAspect {

    @Pointcut("@annotation(mimly.emergencyroom.aspect.WrapMeInArray)")
    public void apiPointCut() {
    }

    @Around("apiPointCut()")
    public Object wrapMeInArray(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        // Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        log.debug(className);
        log.debug(methodName);

        Object object = proceedingJoinPoint.proceed();
        log.debug(object.toString());
        return new Object[]{object};
    }
}

