package br.com.cleanarchitecture.application.usecases.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModel implements Serializable {
	
	private static final long serialVersionUID = -1964781918439676249L;

	private Long id; 
	
	private String name;
	
	private String email;
	
	private String password;
	
	private boolean active;
}
