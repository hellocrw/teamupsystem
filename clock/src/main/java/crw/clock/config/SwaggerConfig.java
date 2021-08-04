package crw.clock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger-ui.html
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

  @Bean
  public Docket createRestApi() {
    List<Parameter> parameterList = new ArrayList<>();

    ParameterBuilder loginUserIdParameter = new ParameterBuilder();
    loginUserIdParameter.name("loginUserId").description("登录用户ID")
      .modelRef(new ModelRef("long"))
      .parameterType("header")
      .required(false).build();

    ParameterBuilder tokenParameter = new ParameterBuilder();
    tokenParameter.name("token").description("token")
      .modelRef(new ModelRef("string"))
      .parameterType("header")
      .required(false).build();
    parameterList.add(loginUserIdParameter.build());
    parameterList.add(tokenParameter.build());

    return new Docket(DocumentationType.SWAGGER_2)
      .apiInfo(apiInfo())
      .select()
      .apis(RequestHandlerSelectors.basePackage("crw.clock.controller"))
      .paths(PathSelectors.any())
      .build()
      .groupName("token认证接口")
      .globalOperationParameters(parameterList);
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
      .title("clock系统接口abi")
      .description("clock后端测试接口")
      .termsOfServiceUrl("none")
      .version("1.0").build();
  }
}