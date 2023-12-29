package idv.maxence2997.dynamicDataSource.domain;

import idv.maxence2997.dynamicDataSource.entity.postgresql.Employer;

public interface EmployerDomainService {
  
  int addEmployer(Employer employer);
  
  Employer findEmployerById(int id);
  
  Employer findEmployerByName(String name);
}
