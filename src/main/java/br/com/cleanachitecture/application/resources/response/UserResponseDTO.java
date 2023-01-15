package br.com.cleanachitecture.application.resources.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO implements Serializable {
	
	private static final long serialVersionUID = 5409460308482368966L;

	private Long code;
	
	private String name;
	
	private String login;
	
	private String password;
	
	private boolean active;
}
