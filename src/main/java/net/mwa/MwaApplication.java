package net.mwa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
//@EnableRedisHttpSession
public class MwaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MwaApplication.class, args);
	}
}
