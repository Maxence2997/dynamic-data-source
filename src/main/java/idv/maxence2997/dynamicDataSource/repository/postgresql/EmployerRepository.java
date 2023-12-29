package idv.maxence2997.dynamicDataSource.repository.postgresql;

import idv.maxence2997.dynamicDataSource.config.DataSourceEnum;
import idv.maxence2997.dynamicDataSource.config.aspect.DataSourceSelector;
import idv.maxence2997.dynamicDataSource.entity.postgresql.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployerRepository extends JpaRepository<Employer, Integer> {
  
  @DataSourceSelector(DataSourceEnum.REPLICA)
  Optional<Employer> findEmployeeByEmployerName(String name);
}
