package person.crw.springdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import person.crw.springdatajpa.entity.Works;

import java.util.List;

/**
 * extends JpaRepository接口
 */
public interface WorksRepository extends JpaRepository<Works,Long> , QuerydslPredicateExecutor<Works> {

  List<Works> findByWorksName(String workSName);

  @Query(value = "select * from works where works_name=?", nativeQuery = true)
  List<Works> queryByNameHQL(String name);

}
