package br.com.cleanachitecture.application.resources.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO implements Serializable {

	private static final long serialVersionUID = 3307193835020076449L;
	
	private String name;
	
	private String email;
	
	private String password;
}
