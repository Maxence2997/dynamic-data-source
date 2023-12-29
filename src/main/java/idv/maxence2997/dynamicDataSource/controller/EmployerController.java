package idv.maxence2997.dynamicDataSource.controller;

import idv.maxence2997.dynamicDataSource.entity.postgresql.Employer;
import idv.maxence2997.dynamicDataSource.service.EmployerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/employer")
@RequiredArgsConstructor
public class EmployerController {
  
  private final EmployerService employerService;
  
  @PostMapping(value = "/add")
  public int addEmployee(@RequestBody Employer employer) {
    return employerService.addEmployer(employer);
  }
  
  @GetMapping("/{id}")
  public Employer findEmployeeById(@PathVariable("id") int id) {
    return employerService.findEmployerById(id);
  }
  
  @GetMapping("/findByName")
  public Employer findEmployeeByName(@RequestParam("name") String name) {
    return employerService.findEmployerByName(name);
  }
}
