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
import java.util.Properties;

@Slf4j
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {Constant.RepositoryDir.MYSQL},
                       entityManagerFactoryRef = Constant.EntityManagerFactory.MYSQL,
                       transactionManagerRef = Constant.MultiTransactionManager.MYSQL)
public class MysqlDynamicSourceConfig extends DynamicDataSourceAbstractConfig {
  
  @Bean(name = Constant.DynamicDataSource.MYSQL)
  public DataSource dynamicDataSource(@Qualifier(Constant.DataSource.Main.MYSQL) DataSource mainDataSource,
                                      @Qualifier(Constant.DataSource.Replica.MYSQL) DataSource replicaDataSource) {
    
    log.info("Constructing mysql dynamic data source...");
    
    DataSource dynamicDataSource = super.prepareDataSourceMap(mainDataSource, replicaDataSource);
    
    log.info("Finished mysql dynamic data source...");
    
    return dynamicDataSource;
  }
  
  @Primary
  @Bean(Constant.EntityManagerFactory.MYSQL)
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
      @Qualifier(Constant.DynamicDataSource.MYSQL) DataSource dynamicDataSource,
      @Qualifier(Constant.HIBERNATE_PROPERTIES) Properties hibernateProperties) {
    
    log.info("Constructing mysql entity manager factory...");
    
    LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactory.setDataSource(dynamicDataSource);
    entityManagerFactory.setPackagesToScan(new String[]{Constant.EntityDir.MYSQL});
    entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    entityManagerFactory.setJpaProperties(hibernateProperties);
    entityManagerFactory.setPersistenceUnitName(Constant.MYSQL);
    
    log.info("Finished mysql entity manager factory...");
    
    return entityManagerFactory;
  }
  
  @Bean(Constant.MultiTransactionManager.MYSQL)
  public PlatformTransactionManager multiTransactionManager(
      @Qualifier(Constant.EntityManagerFactory.MYSQL)
      LocalContainerEntityManagerFactoryBean entityManagerFactory) {
    
    log.info("Constructing mysql multi transaction manager...");
    
    JpaTransactionManager multiTransactionManager = new JpaTransactionManager();
    multiTransactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
    
    log.info("Finished mysql multi transaction manager...");
    
    return multiTransactionManager;
  }
}
