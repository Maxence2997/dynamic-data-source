package idv.maxence2997.dynamicDataSource.controller;

import idv.maxence2997.dynamicDataSource.entity.Employee;
import idv.maxence2997.dynamicDataSource.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
  
  private final EmployeeService employeeService;
  
  @PostMapping(value = "/add")
  public int addEmployee(@RequestBody Employee employee) {
    return employeeService.addEmployee(employee);
  }
  
  @GetMapping("/{id}")
  public Employee findEmployeeById(@PathVariable("id") int id) {
    return employeeService.findEmployeeById(id);
  }
  
  @GetMapping("/findByName")
  public Employee findEmployeeByName(@RequestParam("name") String name) {
    return employeeService.findEmployeeByName(name);
  }
}
