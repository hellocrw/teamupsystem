package person.crw.springdatajpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import person.crw.springdatajpa.entity.ClassInfo;
import person.crw.springdatajpa.entity.Student;
import person.crw.springdatajpa.repository.ClassInfoRepository;

@RestController
@RequestMapping("class-info")
public class ClassInfoController {

  @Autowired
  private ClassInfoRepository classInfoRepository;

  @PostMapping()
  public void saveClassInfo() {
    ClassInfo classInfo = new ClassInfo();
    classInfo.setName("class01");
    Student student = new Student();
    student.setAge("12");
    student.setName("stu12");
    classInfo.getStudents().add(student);
    classInfoRepository.save(classInfo);
  }

}
