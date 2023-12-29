package idv.maxence2997.dynamicDataSource.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Slf4j
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {Constant.RepositoryDir.POSTGRESQL},
                       entityManagerFactoryRef = Constant.EntityManagerFactory.POSTGRESQL,
                       transactionManagerRef = Constant.MultiTransactionManager.POSTGRESQL)
public class PostgresqlDynamicSourceConfig extends DynamicDataSourceAbstractConfig {
  
  @Bean(name = Constant.DynamicDataSource.POSTGRESQL)
  public DataSource dynamicDataSource(@Qualifier(Constant.DataSource.Main.POSTGRESQL) DataSource mainDataSource,
                                      @Qualifier(Constant.DataSource.Replica.POSTGRESQL) DataSource replicaDataSource) {
    
    log.info("Constructing postgresql dynamic data source...");
    
    DataSource dynamicDataSource = super.prepareDataSourceMap(mainDataSource, replicaDataSource);
    
    log.info("Finished postgresql dynamic data source...");
    
    return dynamicDataSource;
  }
  
  //  @Primary //only be added in mysql config, make mysql be connection default
  @Bean(Constant.EntityManagerFactory.POSTGRESQL)
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
      @Qualifier(Constant.DynamicDataSource.POSTGRESQL) DataSource dynamicDataSource,
      @Qualifier(Constant.HIBERNATE_PROPERTIES) Properties hibernateProperties) {
    
    log.info("Constructing postgresql entity manager factory...");
    
    LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactory.setDataSource(dynamicDataSource);
    entityManagerFactory.setPackagesToScan(new String[]{Constant.EntityDir.POSTGRESQL});
    entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    entityManagerFactory.setJpaProperties(hibernateProperties);
    entityManagerFactory.setPersistenceUnitName(Constant.POSTGRESQL);
    
    log.info("Finished postgresql entity manager factory...");
    
    return entityManagerFactory;
  }
  
  @Bean(Constant.MultiTransactionManager.POSTGRESQL)
  public PlatformTransactionManager multiTransactionManager(
      @Qualifier(Constant.EntityManagerFactory.POSTGRESQL)
      LocalContainerEntityManagerFactoryBean entityManagerFactory) {
    
    log.info("Constructing postgresql multi transaction manager...");
    
    JpaTransactionManager multiTransactionManager = new JpaTransactionManager();
    multiTransactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
    
    log.info("Finished postgresql multi transaction manager...");
    
    return multiTransactionManager;
  }
}
