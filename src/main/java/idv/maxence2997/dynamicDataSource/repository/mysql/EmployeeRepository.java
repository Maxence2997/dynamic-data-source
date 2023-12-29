package idv.maxence2997.dynamicDataSource.repository.mysql;

import idv.maxence2997.dynamicDataSource.config.DataSourceEnum;
import idv.maxence2997.dynamicDataSource.config.aspect.DataSourceSelector;
import idv.maxence2997.dynamicDataSource.entity.mysql.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
  
  @DataSourceSelector(DataSourceEnum.REPLICA)
  Optional<Employee> findEmployeeByEmployeeName(String name);
}
