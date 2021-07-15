package person.crw.springdatajpa.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "teacher")
public class Teacher {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "age")
  private String age;

}
