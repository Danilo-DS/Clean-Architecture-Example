package br.com.cleanarchitecture.application.utils;

import java.util.List;
import java.util.stream.Collectors;

import br.com.cleanarchitecture.application.entities.UserEntity;
import br.com.cleanarchitecture.application.resources.request.UserRequestDTO;
import br.com.cleanarchitecture.application.resources.response.UserResponseDTO;
import br.com.cleanarchitecture.application.usecases.models.UserModel;

public class UserUtils {
	
	public static UserModel userEntityToModel(UserEntity user) {
		return UserModel.builder()
				.id(user.getId())
				.name(user.getName())
				.email(user.getEmail())
				.password(user.getPassword())
				.active(user.isActive())
				.build();
	}
	
	public static UserResponseDTO userModelToResponseDTO(UserModel user) {
		return UserResponseDTO.builder()
				.code(user.getId())
				.name(user.getName())
				.login(user.getEmail())
				.password(user.getPassword())
				.active(user.isActive())
				.build();
	}
	
	public static UserModel userRequestDtoToModel(UserRequestDTO userRequest) {
		return UserModel.builder()
				.name(userRequest.getName())
				.email(userRequest.getEmail())
				.password(userRequest.getPassword())
				.build();
	}
	
	public static UserEntity userModelToEntity(UserModel userModel) {
		return UserEntity.builder()
				.name(userModel.getName())
				.email(userModel.getEmail())
				.password(userModel.getPassword())
				.build();
	}
	
	public static List<UserResponseDTO> convertUserListToDTO(List<UserEntity> users) {
		return users.stream().map(u -> {
			UserModel userModel = userEntityToModel(u);
			return userModelToResponseDTO(userModel);
		})
		.collect(Collectors.toList());
	}
}
