package net.mwa;

import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("net.mwa"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
    
    
    private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Micro Services for MWA requirement")
				.description("Makning membership and paymenst dues avaialble online")
				.contact(new Contact("Swamy Kunta", "http://mayurinagar.hyd", "swamy.kunta@gmail.com"))
				.license("MWA License")
				.licenseUrl("http://mayurinagar.hyd").version("1.0").build();
	}
    
}
