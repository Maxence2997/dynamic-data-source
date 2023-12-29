package idv.maxence2997.dynamicDataSource.service.impl;

import idv.maxence2997.dynamicDataSource.domain.EmployeeDomainService;
import idv.maxence2997.dynamicDataSource.entity.mysql.Employee;
import idv.maxence2997.dynamicDataSource.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
  
  private final EmployeeDomainService employeeDomainService;
  
  @Override
  public int addEmployee(Employee employee) {
    return employeeDomainService.addEmployee(employee);
  }
  
  @Override
  public Employee findEmployeeById(int id) {
    return employeeDomainService.findEmployeeById(id);
  }
  
  @Override
  public Employee findEmployeeByName(String name) {
    return employeeDomainService.findEmployeeByName(name);
  }
  
}
