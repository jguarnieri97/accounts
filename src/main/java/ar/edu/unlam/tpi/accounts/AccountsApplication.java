package ar.edu.unlam.tpi.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(AccountsApplication.class);
		app.setAdditionalProfiles("test"); // activa el perfil "test"
		app.run(args);
	}
}
