package person.crw.springdatajpa.controller;

import com.alibaba.fastjson.JSON;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import person.crw.springdatajpa.dto.WorksDto;
import person.crw.springdatajpa.entity.QWorks;
import person.crw.springdatajpa.entity.Works;
import person.crw.springdatajpa.repository.WorksRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/works")
public class WorksController {

  @Autowired
  private WorksRepository worksRepository;

  @Autowired
  private JPAQueryFactory jpaQueryFactory;

  @PostMapping()
  public Works saveWorks(@RequestBody Works works) {
    return worksRepository.save(works);
  }

  @GetMapping("/{id}")
  public Works getWorksInfo(@PathVariable("id") Long workId) {
    Optional<Works> optionalWorks = worksRepository.findById(workId);
    return optionalWorks.orElseGet(Works::new);
  }

  @GetMapping("/select/{worksName}")
  public List<Works> getWorksByName(@PathVariable("worksName") String worksName) {
    List<Works> byWorksNames = worksRepository.findByWorksName(worksName);
    return byWorksNames;
  }

  @GetMapping("/sql/{name}")
  public List<Works> queryWorksByName(@PathVariable("name") String name) {
    return worksRepository.queryByNameHQL(name);
  }

  @GetMapping("/query-dsl/{name}")
  public Works queryWorksByDsl(@PathVariable("name") String name) {
    QWorks works = QWorks.works;
    return jpaQueryFactory.selectFrom(works)
      .where(works.worksName.eq(name)).fetchOne();
  }

  @GetMapping("/query-dsl/all")
  public List<Works> getWOWorksList() {
    QWorks works = QWorks.works;
    return jpaQueryFactory.selectFrom(works)
      .orderBy(works.worksId.asc()).fetch();
  }

  @GetMapping("/query-dsl/pageWorks")
  public QueryResults<Works> pageWorksList(Pageable pageable) {
    QWorks works = QWorks.works;
    return jpaQueryFactory.selectFrom(works)
      .orderBy(works.worksId.desc())
      .offset(pageable.getOffset())
      .limit(pageable.getPageSize())
      .fetchResults();
  }

  @GetMapping("/test")
  public void getData() {
    QWorks works = QWorks.works;
    List<WorksDto> worksDtoList = jpaQueryFactory.select(works.worksContent, works.worksUrl).from(works)
      .where(works.worksName.eq("crw")).fetch().stream().map(tuple -> WorksDto.builder()
        .worksContent(tuple.get(works.worksContent))
        .worksUrl(tuple.get(works.worksUrl)).build()
      ).collect(Collectors.toList());

    System.out.println(JSON.toJSONString(worksDtoList));
  }

}
