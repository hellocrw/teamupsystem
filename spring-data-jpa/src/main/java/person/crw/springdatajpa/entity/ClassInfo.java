package person.crw.springdatajpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "class_info")
public class ClassInfo {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @OneToMany(targetEntity = Student.class, cascade = CascadeType.ALL)
  @JoinColumn(name = "class_id", referencedColumnName = "id")
  private List<Student> students = new ArrayList<>();
}
