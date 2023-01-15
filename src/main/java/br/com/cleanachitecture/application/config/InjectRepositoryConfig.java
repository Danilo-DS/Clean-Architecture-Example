package br.com.cleanachitecture.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.cleanachitecture.application.adapters.UserRepositoryImpl;
import br.com.cleanachitecture.application.adapters.interfaces.UserRepositoryInterface;
import br.com.cleanachitecture.application.adapters.repository.UserRepository;;

@Configuration
public class InjectRepositoryConfig {
	
	@Autowired
	private UserRepository userRepository;
	
	@Bean
	public UserRepositoryInterface userRepositoryInterfaceBean() {
		return new UserRepositoryImpl(userRepository);
	}
}
