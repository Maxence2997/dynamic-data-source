package idv.maxence2997.dynamicDataSource.domain.impl;

import idv.maxence2997.dynamicDataSource.config.DataSourceEnum;
import idv.maxence2997.dynamicDataSource.config.aspect.DataSourceSelector;
import idv.maxence2997.dynamicDataSource.domain.EmployeeDomainService;
import idv.maxence2997.dynamicDataSource.entity.mysql.Employee;
import idv.maxence2997.dynamicDataSource.repository.mysql.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class EmployeeDomainServiceImpl implements EmployeeDomainService {
  
  private final EmployeeRepository employeeRepository;
  
  @Override
  public int addEmployee(Employee employee) {
    return employeeRepository.save(employee)
                             .getEmployeeId();
  }
  
  @Override
  @DataSourceSelector(DataSourceEnum.REPLICA)
  public Employee findEmployeeById(int id) {
    return employeeRepository.findById(id)
                             .orElse(null);
  }
  
  @Override
  public Employee findEmployeeByName(String name) {
    return employeeRepository.findEmployeeByEmployeeName(name)
                             .orElse(null);
  }
}
