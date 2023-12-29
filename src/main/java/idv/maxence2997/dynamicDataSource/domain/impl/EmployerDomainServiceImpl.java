package idv.maxence2997.dynamicDataSource.domain.impl;

import idv.maxence2997.dynamicDataSource.config.DataSourceEnum;
import idv.maxence2997.dynamicDataSource.config.aspect.DataSourceSelector;
import idv.maxence2997.dynamicDataSource.domain.EmployerDomainService;
import idv.maxence2997.dynamicDataSource.entity.postgresql.Employer;
import idv.maxence2997.dynamicDataSource.repository.postgresql.EmployerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class EmployerDomainServiceImpl implements EmployerDomainService {
  
  private final EmployerRepository employerRepository;
  
  @Override
  public int addEmployer(Employer employer) {
    return employerRepository.save(employer)
                             .getEmployerId();
  }
  
  @Override
  @DataSourceSelector(DataSourceEnum.REPLICA)
  public Employer findEmployerById(int id) {
    return employerRepository.findById(id)
                             .orElse(null);
  }
  
  @Override
  public Employer findEmployerByName(String name) {
    return employerRepository.findEmployeeByEmployerName(name)
                             .orElse(null);
  }
}
