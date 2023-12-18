package idv.maxence2997.dynamicDataSource;

import idv.maxence2997.dynamicDataSource.entity.Employee;
import idv.maxence2997.dynamicDataSource.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DynamicDataSourceLearningApplication {
  
  public static void main(String[] args) {
    SpringApplication.run(DynamicDataSourceLearningApplication.class, args);
  }
}
