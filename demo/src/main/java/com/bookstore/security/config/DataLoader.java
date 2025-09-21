package com.bookstore.security.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.bookstore.entity.User;
import com.bookstore.repository.UserRepository;

/*
 * Load initial data into the database at application startup.
 * Creating an admin user by default
 */
@Component
public class DataLoader implements CommandLineRunner{

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123")); // ✅ ENCODE the password
            admin.setRole("ADMINISTRATOR");
            userRepository.save(admin);
        }
    }

}
