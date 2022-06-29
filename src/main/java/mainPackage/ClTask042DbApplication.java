package mainPackage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan("mainPackage.entities.product")
@ComponentScan("mainPackage.entities.product")
public class ClTask042DbApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClTask042DbApplication.class, args);
	}

}
