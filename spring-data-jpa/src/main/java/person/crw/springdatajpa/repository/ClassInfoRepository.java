package person.crw.springdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import person.crw.springdatajpa.entity.ClassInfo;

public interface ClassInfoRepository extends JpaRepository<ClassInfo, Long> {
}
