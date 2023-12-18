package idv.maxence2997.dynamicDataSource.domain;

import idv.maxence2997.dynamicDataSource.entity.Employee;

public interface EmployeeDomainService {
  int addEmployee(Employee employee);
  Employee findEmployeeById(int id);
  Employee findEmployeeByName(String name);
}
