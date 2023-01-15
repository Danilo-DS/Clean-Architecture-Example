package br.com.cleanachitecture.application.utils;

import br.com.cleanachitecture.application.entities.UserEntity;
import br.com.cleanachitecture.application.resources.request.UserRequestDTO;
import br.com.cleanachitecture.application.resources.response.UserResponseDTO;

public class UserUtils {
	
	public static UserResponseDTO entityToUserReponseDto(UserEntity user) {
		return UserResponseDTO.builder()
				.code(user.getId())
				.name(user.getName())
				.login(user.getEmail())
				.password(user.getPassword())
				.active(user.isActive())
				.build();
	}
	
	public static UserEntity userRequestDtoToEntity(UserRequestDTO userRequest) {
		return UserEntity.builder()
				.name(userRequest.getName())
				.email(userRequest.getEmail())
				.password(userRequest.getPassword())
				.build();
	}
}
