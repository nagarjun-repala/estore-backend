package com.nagarjun.estorebackend;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.boot.CommandLineRunner;
import com.nagarjun.estorebackend.entity.Product;
import com.nagarjun.estorebackend.entity.Role;
import com.nagarjun.estorebackend.entity.User;
import com.nagarjun.estorebackend.repository.ProductRepository;
import com.nagarjun.estorebackend.repository.RoleRepository;
import com.nagarjun.estorebackend.repository.UserRepository;
import com.nagarjun.estorebackend.security.SecurityConstants;

@SpringBootApplication
public class EstoreBackendApplication implements CommandLineRunner{

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

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
		roleRepository.save(new Role("ADMIN"));
		roleRepository.save(new Role("USER"));
		Role role = roleRepository.findById(SecurityConstants.ROLE_ADMIN_ID).get();

		User[] users = new User[] {			
			new User("admin", "admin", "admin", bCryptPasswordEncoder().encode("admin"), "admin@admin.com", GlobalMethods.dateTimeFormatter(LocalDateTime.now()))
		};

		
		for (User user : users) {
			role.getUsers().add(user);
			userRepository.save(user);
			roleRepository.save(role);
		}

	}
}
