package person.crw.springdatajpa.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@ToString
@Table(name = "works")
public class Works {

  @Id
  @Column(name = "works_id")
  private Long worksId;

  @Column(name = "works_name")
  private String worksName;

  @Column(name = "works_content")
  private String worksContent;

  @Column(name = "works_url")
  private String worksUrl;
}
