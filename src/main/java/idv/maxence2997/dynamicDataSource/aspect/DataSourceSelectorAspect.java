package idv.maxence2997.dynamicDataSource.aspect;

import idv.maxence2997.dynamicDataSource.config.DatabaseContextHolder;
import idv.maxence2997.dynamicDataSource.config.aspect.DataSourceSelector;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class DataSourceSelectorAspect {
  
  /**
   * choose one to use between this and DataSourceSelectorAdvisor.java, but this one has better perf. during
   * application start time.
   */
  
  @Pointcut("@annotation(idv.maxence2997.dynamicDataSource.config.aspect.DataSourceSelector)")
  public void dataSourceSelectedMethod() {}
  
  @Around("dataSourceSelectedMethod()")
  public Object modifyTargetDataSource(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    
    MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
    DataSourceSelector dataSourceSelector = AnnotationUtils.getAnnotation(methodSignature.getMethod(),
                                                                          DataSourceSelector.class);
    log.info("matched method for @DataSourceSelector, method: {}, targetClass: {}", methodSignature.getMethod(),
             proceedingJoinPoint.getTarget());
    try {
      assert dataSourceSelector != null;
      DatabaseContextHolder.setDatabaseContextHolder(dataSourceSelector.value());
      return proceedingJoinPoint.proceed();
    } finally {
      DatabaseContextHolder.clear();
    }
  }
}
