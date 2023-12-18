package idv.maxence2997.dynamicDataSource.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ReplicaDataSourceConfig {
  
  @Bean
  @ConfigurationProperties(prefix = "spring.dynamic-data-source.replica")
  public DataSourceProperties replicaDataSourceProperties() {
    return new DataSourceProperties();
  }
  
  @Bean
  public DataSource replicaDataSource(DataSourceProperties replicaDataSourceProperties) {
    return replicaDataSourceProperties.initializeDataSourceBuilder()
                                       .type(HikariDataSource.class)
                                       .build();
  }
}
