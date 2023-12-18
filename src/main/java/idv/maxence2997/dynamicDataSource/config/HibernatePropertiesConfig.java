package idv.maxence2997.dynamicDataSource.config;

import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;
import java.util.function.Supplier;

@Configuration
public class HibernatePropertiesConfig {
  
  private static final String HIBERNATE_DDL_AUTO_DEFAULT = "none";
  private static final String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
  
  @Bean("hibernateProperties")
  public Properties hibernateProperties(HibernateProperties hibernateProperties, JpaProperties jpaProperties) {
    
    Properties properties = new Properties();
    
    this.applyStrategy(properties,
                       AvailableSettings.HBM2DDL_AUTO,
                       hibernateProperties.getDdlAuto(),
                       () -> HIBERNATE_DDL_AUTO_DEFAULT);
    
    this.applyStrategy(properties,
                       AvailableSettings.PHYSICAL_NAMING_STRATEGY,
                       hibernateProperties.getNaming()
                                          .getPhysicalStrategy(),
                       CamelCaseToUnderscoresNamingStrategy.class::getName);
    
    this.applyStrategy(properties,
                       AvailableSettings.IMPLICIT_NAMING_STRATEGY,
                       hibernateProperties.getNaming()
                                          .getImplicitStrategy(),
                       SpringImplicitNamingStrategy.class::getName);
    
    this.applyStrategy(properties,
                       AvailableSettings.SHOW_SQL,
                       jpaProperties.isShowSql(),
                       Boolean.FALSE::toString);
    
    this.applyStrategy(properties,
                       AvailableSettings.FORMAT_SQL,
                       jpaProperties.getProperties()
                                    .get(HIBERNATE_FORMAT_SQL),
                       Boolean.FALSE::toString);
    
    return properties;
  }
  
  private void applyStrategy(Properties properties, String key, Object strategy,
                             Supplier<String> defaultStrategy) {
    if (strategy != null) {
      properties.put(key, strategy);
    } else {
      properties.computeIfAbsent(key, (k) -> defaultStrategy.get());
    }
  }
}
