package idv.maxence2997.dynamicDataSource.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

import static idv.maxence2997.dynamicDataSource.config.Constant.*;

@Configuration
public class PostgresqlDataSourceConfig {
  
  private static final String DATABASE_TYPE = POSTGRESQL;
  private static final String MAIN_PREFIX =
      PROPERTY_PREFIX + SEPARATOR + DATABASE_TYPE + SEPARATOR + MAIN_LOW;
  private static final String REPLICA_PREFIX =
      PROPERTY_PREFIX + SEPARATOR + DATABASE_TYPE + SEPARATOR + REPLICA_LOW;
  
  @Bean(Constant.DataSourceProperties.Main.POSTGRESQL)
  @ConfigurationProperties(prefix = MAIN_PREFIX)
  public DataSourceProperties mainDataSourceProperties() {
    return new DataSourceProperties();
  }
  
  //  @Primary  // cannot be removed!!
  @Bean(Constant.DataSource.Main.POSTGRESQL)
  public DataSource mainDataSource(
      @Qualifier(Constant.DataSourceProperties.Main.POSTGRESQL)
      DataSourceProperties mainDataSourceProperties) {
    return mainDataSourceProperties.initializeDataSourceBuilder()
                                   .type(HikariDataSource.class)
                                   .build();
  }
  
  @Bean(Constant.DataSourceProperties.Replica.POSTGRESQL)
  @ConfigurationProperties(prefix = REPLICA_PREFIX)
  public DataSourceProperties replicaDataSourceProperties() {
    return new DataSourceProperties();
  }
  
  @Bean(Constant.DataSource.Replica.POSTGRESQL)
  public DataSource replicaDataSource(
      @Qualifier(Constant.DataSourceProperties.Replica.POSTGRESQL)
      DataSourceProperties replicaDataSourceProperties) {
    return replicaDataSourceProperties.initializeDataSourceBuilder()
                                      .type(HikariDataSource.class)
                                      .build();
  }
}
