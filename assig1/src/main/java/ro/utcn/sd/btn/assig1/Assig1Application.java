package ro.utcn.sd.btn.assig1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ro.utcn.sd.btn.assig1.controller.ConsoleController;

@SpringBootApplication
public class Assig1Application {

	public static void main(String[] args) {
		SpringApplication.run(Assig1Application.class, args);
	}

}
