package idv.maxence2997.dynamicDataSource.config;

import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public abstract class DynamicDataSourceAbstractConfig {
  
  protected DataSource prepareDataSourceMap(DataSource mainDataSource,
                                            DataSource replicaDataSource) {
    
    Map<Object, Object> dataSourceMap = new HashMap<>();
    dataSourceMap.put(DataSourceEnum.MAIN, mainDataSource);
    dataSourceMap.put(DataSourceEnum.REPLICA, replicaDataSource);
    DynamicDataSource dynamicDataSource = new DynamicDataSource();
    dynamicDataSource.setDefaultTargetDataSource(mainDataSource);
    dynamicDataSource.setTargetDataSources(dataSourceMap);
    
    return dynamicDataSource;
  }
  
  public abstract DataSource dynamicDataSource(DataSource mainDataSource, DataSource replicaDataSource);
  
  public abstract LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dynamicDataSource,
                                                                              Properties hibernateProperties);
  
  public abstract PlatformTransactionManager multiTransactionManager(
      LocalContainerEntityManagerFactoryBean entityManagerFactory);
}
