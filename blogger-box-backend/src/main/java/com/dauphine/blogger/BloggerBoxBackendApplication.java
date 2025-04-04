package com.dauphine.blogger;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(
			title = "Blogger box backed",
			description = "Blogger bow endpoints and apis",
			contact = @Contact(name = "Justine", email = "rault.justine.77@gmail.com"),
			version = "1.0.0"

	)
)
public class BloggerBoxBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloggerBoxBackendApplication.class, args);
	}

}
