package sqc.goods.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description: Swagger2的配置
 * @author: Sqcode
 * @since: 2021/5/10 13:04
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket docker() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("sqc.goods"))
                .paths(PathSelectors.any())
                .build().enable(true);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("商品管理")
                .description("商品管理")
                .termsOfServiceUrl("111")
                .version("2.0")
                .build();
    }

}
