package idv.maxence2997.dynamicDataSource.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
  
  @Override
  protected Object determineCurrentLookupKey() {
    return DatabaseContextHolder.getDatabaseContext();
  }
}
