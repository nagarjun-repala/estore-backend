package com.nagarjun.estorebackend;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.boot.CommandLineRunner;
import com.nagarjun.estorebackend.entity.Product;
import com.nagarjun.estorebackend.entity.User;
import com.nagarjun.estorebackend.repository.ProductRepository;
import com.nagarjun.estorebackend.repository.UserRepository;

@SpringBootApplication
public class EstoreBackendApplication implements CommandLineRunner{

	@Autowired
	ProductRepository productRepository;

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(EstoreBackendApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {
		Product[] products = new Product[] {
			new Product("Table", "It is a table", 1000),
			new Product("Chair", "It is a chair", 500),
			new Product("Iphone-14", "It is a phone", 50000),

		};

		for (Product product : products) {
			productRepository.save(product);
		}

		User[] users = new User[] {			
			new User("Nagarjun", "Repala", "nagarjun.repala", "password", "nagarjun.repala@outlook.com", LocalDateTime.now()),
			new User("Test", "Test", "teset.test", "test", "test.test@outlook.com", LocalDateTime.now())			
		};

		for (User user : users) {
			userRepository.save(user);
		}
	}
}
