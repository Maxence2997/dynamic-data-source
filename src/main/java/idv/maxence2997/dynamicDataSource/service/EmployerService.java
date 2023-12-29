package idv.maxence2997.dynamicDataSource.service;

import idv.maxence2997.dynamicDataSource.entity.postgresql.Employer;

public interface EmployerService {
  
  int addEmployer(Employer employer);
  
  Employer findEmployerById(int id);
  
  Employer findEmployerByName(String name);
}
