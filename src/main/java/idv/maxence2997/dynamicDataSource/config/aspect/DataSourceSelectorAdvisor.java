package idv.maxence2997.dynamicDataSource.config.aspect;

import idv.maxence2997.dynamicDataSource.config.Constants;
import idv.maxence2997.dynamicDataSource.config.DatabaseContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Role;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Component
@Role(BeanDefinition.ROLE_INFRASTRUCTURE) // specify that this class should not be proxy.
public class DataSourceSelectorAdvisor extends AbstractBeanFactoryPointcutAdvisor {
  
  /**
   * Let it scan the target classes be proxied for jpa repository interface.
   */
  private static final String JDK_PROXY_CLASS_KEYWORD = "jdk.proxy";
  private final Advice advice = new DataSourceSelectorInterceptor();
  private final StaticMethodMatcherPointcut pointcut = new StaticMethodMatcherPointcut() {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
      
      boolean hasAnnotation = false;
      String className = targetClass.getName();
      if (className.startsWith(Constants.BASE_PACKAGE_DIR) || className.contains(JDK_PROXY_CLASS_KEYWORD)) {
        // 直接使用spring工具包，来获取method上的注解（会找父类上的注解）
        hasAnnotation = AnnotatedElementUtils.hasAnnotation(method, DataSourceSelector.class);
        if (hasAnnotation) {
          log.info("matched method for @DataSourceSelector, method: {}, targetClass: {}", method, targetClass);
        }
      }
      return hasAnnotation;
    }
  };
  
  @Override
  public Pointcut getPointcut() {
    return this.pointcut;
  }
  
  @Override
  public Advice getAdvice() {
    return this.advice;
  }
  
  private static class DataSourceSelectorInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
      
      try {
        DataSourceSelector dataSourceSelector = invocation.getMethod()
                                                          .getAnnotation(DataSourceSelector.class);
        DatabaseContextHolder.setDatabaseContextHolder(dataSourceSelector.value());
        return invocation.proceed();
      } finally {
        DatabaseContextHolder.clear();
      }
    }
  }
}
