package idv.maxence2997.dynamicDataSource.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {Constants.BASE_PACKAGE_DIR},
                       entityManagerFactoryRef = "customizedEntityManagerFactory",
                       transactionManagerRef = "multiTransactionManager")
public class DynamicDataSourceConfig {
  
  @Bean(name = "dynamicDataSource")
  public DataSource dynamicDataSource(DataSource mainDataSource,
                                      @Qualifier("replicaDataSource") DataSource replicaDataSource) {
    Map<Object, Object> dataSourceMap = new HashMap<>();
    dataSourceMap.put(DataSourceEnum.MAIN, mainDataSource);
    dataSourceMap.put(DataSourceEnum.REPLICA, replicaDataSource);
    DynamicDataSource dynamicDataSource = new DynamicDataSource();
    dynamicDataSource.setDefaultTargetDataSource(mainDataSource);
    dynamicDataSource.setTargetDataSources(dataSourceMap);
    return dynamicDataSource;
  }
  
  @Primary
  @Bean
  public LocalContainerEntityManagerFactoryBean customizedEntityManagerFactory(
    @Qualifier("dynamicDataSource") DataSource dynamicDataSource,
    Properties hibernateProperties) {
    
    log.info("Constructing customizedEntityManagerFactory...");
    
    LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactory.setDataSource(dynamicDataSource);
    entityManagerFactory.setPackagesToScan(new String[]{Constants.ENTITY_DIR});
    entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    entityManagerFactory.setJpaProperties(hibernateProperties);
    log.info("Finished customizedEntityManagerFactory...");
    
    return entityManagerFactory;
  }
  
  @Bean
  public PlatformTransactionManager multiTransactionManager(
    LocalContainerEntityManagerFactoryBean customizedEntityManagerFactory) {
    
    log.info("Constructing multiTransactionManager...");
    
    JpaTransactionManager multiTransactionManager = new JpaTransactionManager();
    multiTransactionManager.setEntityManagerFactory(customizedEntityManagerFactory.getObject());
    
    log.info("Finished multiTransactionManager...");
    
    return multiTransactionManager;
  }
  
}
