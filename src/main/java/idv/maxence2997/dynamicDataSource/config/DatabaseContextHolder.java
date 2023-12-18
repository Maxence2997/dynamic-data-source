package idv.maxence2997.dynamicDataSource.config;

public class DatabaseContextHolder {
  private static final ThreadLocal<DataSourceEnum> DATABASE_CONTEXT_HOLDER = new ThreadLocal<>();
  
  public static void setDatabaseContextHolder(DataSourceEnum dataSourceEnum) {
    DATABASE_CONTEXT_HOLDER.set(dataSourceEnum);
  }
  
  public static DataSourceEnum getDatabaseContext() {
    return DATABASE_CONTEXT_HOLDER.get();
  }
  
  public static void clear() {
    DATABASE_CONTEXT_HOLDER.remove();
  }
}
