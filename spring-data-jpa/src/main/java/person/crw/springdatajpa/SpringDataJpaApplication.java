package person.crw.springdatajpa;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;

@SpringBootApplication
public class SpringDataJpaApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringDataJpaApplication.class, args);
  }

  /**
   * 让spring容器管理JPAQueryFactory
   * 使用QueryDSL功能时，会依赖使用到JPAQueryFactory,而JPAQueryFactory在这里以来使用EntityManager，所以在主类中配置如下配置，使得spring自动注入EntityManager与自动管理JPAQueryFactory
   * @param entityManager
   * @return
   */
  @Bean
  public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
    return new JPAQueryFactory(entityManager);
  }

}
