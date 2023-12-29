package idv.maxence2997.dynamicDataSource.entity.postgresql;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Employer {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "employer_id")
  private int employerId;
  
  @Column(name = "employer_name")
  private String employerName;
  
  @Column(name = "employer_role")
  private String employerRole;
  
  @Column(name = "employer_performance")
  private String employerPerformance;
}
