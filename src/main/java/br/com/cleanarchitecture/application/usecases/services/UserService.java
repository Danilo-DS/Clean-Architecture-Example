package br.com.cleanarchitecture.application.usecases.services;

import java.util.List;

import br.com.cleanarchitecture.application.resources.request.UserRequestDTO;
import br.com.cleanarchitecture.application.resources.response.UserResponseDTO;

public interface UserService {
	
	List<UserResponseDTO> listAll();
	
	UserResponseDTO findById(Long id);
	
	UserResponseDTO save(UserRequestDTO userRequest);
	
	UserResponseDTO update(Long id, UserRequestDTO userRequest);
	
	void activeUser(Long id);
	
	void inactiveUser(Long id);
}
