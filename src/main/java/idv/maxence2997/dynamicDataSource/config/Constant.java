package idv.maxence2997.dynamicDataSource.config;

public class Constant {
  
  public static final String SEPARATOR = ".";
  public static final String BASE_PACKAGE_DIR = "idv.maxence2997.dynamicDataSource";
  public static final String PROPERTY_PREFIX = "spring.dynamic-data-source";
  public static final String HIBERNATE_PROPERTIES = "CustomizedHibernateProperties";
  public static final String HIBERNATE_DIALECT = "hibernate.dialect";
  public static final String MAIN = "Main";
  public static final String MAIN_LOW = "main";
  public static final String REPLICA = "Replica";
  public static final String REPLICA_LOW = "replica";
  public static final String MYSQL = "mysql";
  public static final String POSTGRESQL = "postgresql";
  private static final String ENTITY = "entity";
  private static final String REPOSITORY = "repository";
  
  public static class EntityDir {
    private static final String BASE = BASE_PACKAGE_DIR + SEPARATOR + ENTITY;
    public static final String MYSQL = BASE + SEPARATOR + Constant.MYSQL;
    public static final String POSTGRESQL = BASE + SEPARATOR + Constant.POSTGRESQL;
  }
  
  public static class RepositoryDir {
    private static final String BASE = BASE_PACKAGE_DIR + SEPARATOR + REPOSITORY;
    public static final String MYSQL = BASE + SEPARATOR + Constant.MYSQL;
    public static final String POSTGRESQL = BASE + SEPARATOR + Constant.POSTGRESQL;
  }
  
  public static class EntityManagerFactory {
    
    private static final String BASE = "EntityManagerFactory";
    public static final String MYSQL = Constant.MYSQL + BASE;
    public static final String POSTGRESQL = Constant.POSTGRESQL + BASE;
  }
  
  public static class MultiTransactionManager {
    private static final String BASE = "MultiTransactionManager";
    public static final String MYSQL = Constant.MYSQL + BASE;
    public static final String POSTGRESQL = Constant.POSTGRESQL + BASE;
  }
  
  public static class DynamicDataSource {
    
    private static final String BASE = "DynamicDataSource";
    public static final String MYSQL = Constant.MYSQL + BASE;
    public static final String POSTGRESQL = Constant.POSTGRESQL + BASE;
  }
  
  public static class DataSource {
    private static final String BASE = "DataSource";
    
    public static class Main {
      public static final String MYSQL = Constant.MYSQL + Constant.MAIN + DataSource.BASE;
      public static final String POSTGRESQL = Constant.POSTGRESQL + BASE + DataSource.BASE;
    }
    
    public static class Replica {
      public static final String MYSQL = Constant.MYSQL + Constant.REPLICA + DataSource.BASE;
      public static final String POSTGRESQL = Constant.POSTGRESQL + BASE + DataSource.BASE;
    }
  }
  
  public static class DataSourceProperties {
    
    private static final String BASE = "DataSourceProperties";
    
    public static class Main {
      public static final String MYSQL = Constant.MYSQL + Constant.MAIN + DataSourceProperties.BASE;
      public static final String POSTGRESQL = Constant.POSTGRESQL + BASE + DataSourceProperties.BASE;
    }
    
    public static class Replica {
      public static final String MYSQL = Constant.MYSQL + Constant.REPLICA + DataSourceProperties.BASE;
      public static final String POSTGRESQL = Constant.POSTGRESQL + BASE + DataSourceProperties.BASE;
    }
  }
}
