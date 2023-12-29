package idv.maxence2997.dynamicDataSource.service;

import idv.maxence2997.dynamicDataSource.entity.mysql.Employee;

public interface EmployeeService {
  
  int addEmployee(Employee employee);
  
  Employee findEmployeeById(int id);
  
  Employee findEmployeeByName(String name);
}
