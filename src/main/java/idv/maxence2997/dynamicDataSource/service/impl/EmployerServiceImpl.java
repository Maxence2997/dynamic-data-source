package idv.maxence2997.dynamicDataSource.service.impl;

import idv.maxence2997.dynamicDataSource.domain.EmployerDomainService;
import idv.maxence2997.dynamicDataSource.entity.postgresql.Employer;
import idv.maxence2997.dynamicDataSource.service.EmployerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployerServiceImpl implements EmployerService {
  
  private final EmployerDomainService employerDomainService;
  
  @Override
  public int addEmployer(Employer employer) {
    return employerDomainService.addEmployer(employer);
  }
  
  @Override
  public Employer findEmployerById(int id) {
    return employerDomainService.findEmployerById(id);
  }
  
  @Override
  public Employer findEmployerByName(String name) {
    return employerDomainService.findEmployerByName(name);
  }
}
