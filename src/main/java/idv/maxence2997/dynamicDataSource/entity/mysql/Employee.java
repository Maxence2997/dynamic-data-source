package idv.maxence2997.dynamicDataSource.entity.mysql;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Employee {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "employee_id")
  private int employeeId;
  
  @Column(name = "employee_name")
  private String employeeName;
  
  @Column(name = "employee_role")
  private String employeeRole;
  
  @Column(name = "employee_performance")
  private String employeePerformance;
  
}
