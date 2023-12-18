package idv.maxence2997.dynamicDataSource.config.aspect;

import idv.maxence2997.dynamicDataSource.config.DataSourceEnum;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DataSourceSelector {
  
  DataSourceEnum value() default DataSourceEnum.MAIN;
}
