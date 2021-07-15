package person.crw.springdatajpa.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "student")
public class Student {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "age")
  private String age;

  @ManyToOne(targetEntity = ClassInfo.class)  // 表示多方
  @JoinColumn(name = "class_id", referencedColumnName = "id")  // 维护外键，外键在Student中
  private ClassInfo classInfo;

}
