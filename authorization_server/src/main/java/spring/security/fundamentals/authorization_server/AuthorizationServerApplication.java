package spring.security.fundamentals.authorization_server;

import java.util.List;
import java.util.Optional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import spring.security.fundamentals.authorization_server.entity.Client;
import spring.security.fundamentals.authorization_server.entity.User;
import spring.security.fundamentals.authorization_server.repository.ClientRepository;
import spring.security.fundamentals.authorization_server.repository.UserRepository;

@SpringBootApplication
public class AuthorizationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServerApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserRepository userRepository, ClientRepository clientRepository) {
		return args -> {
			Optional<User> krizalis = userRepository.findByUsername("krizalis");
			List<User> all = userRepository.findAll();
			List<Client> all1 = clientRepository.findAll();

			System.out.println();
		};
	}

}
