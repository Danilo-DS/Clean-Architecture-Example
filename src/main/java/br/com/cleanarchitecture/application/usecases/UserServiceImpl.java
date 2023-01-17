package br.com.cleanarchitecture.application.usecases;

import static br.com.cleanarchitecture.application.utils.UserUtils.*;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.cleanarchitecture.application.adapters.repository.UserRepositoryAdapter;
import br.com.cleanarchitecture.application.resources.request.UserRequestDTO;
import br.com.cleanarchitecture.application.resources.response.UserResponseDTO;
import br.com.cleanarchitecture.application.usecases.models.UserModel;
import br.com.cleanarchitecture.application.usecases.services.UserService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepositoryAdapter repository;
	
	@Override
	public List<UserResponseDTO> listAll() {
		return convertUserListToDTO(repository.listAll());
	}

	@Override
	public UserResponseDTO findById(Long id) {
		
		UserModel userModel = userEntityToModel(
			repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
		);
		
		return userModelToResponseDTO(userModel);
	}

	@Override
	public UserResponseDTO save(UserRequestDTO userRequest) {
		
		UserModel userModel = userRequestDtoToModel(userRequest);
		userModel.setActive(Boolean.TRUE);
		
		UserModel userModelRegistration = userEntityToModel(repository.save(userModelToEntity(userModel)));
		
		return userModelToResponseDTO(userModelRegistration);
	}
	
	@Override
	public UserResponseDTO update(Long id, UserRequestDTO userRequest) {
		
		if(repository.existUserById(id)) {
			
			UserModel userModel = userEntityToModel(repository.findById(id).get());
			
			BeanUtils.copyProperties(userRequestDtoToModel(userRequest), userModel, "id", "active");
			
			UserModel userModelRegistration = userEntityToModel(repository.save(userModelToEntity(userModel)));
			
			return userModelToResponseDTO(userModelRegistration);
		}
		
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
	}

	@Override
	public void activeUser(Long id) {
		
		if(!repository.existUserByIdAndInactive(id)) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		repository.active(id);
	}

	@Override
	public void inactiveUser(Long id) {
		
		if(!repository.existUserByIdAndActive(id)) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		repository.inactive(id);
		
	}
	
}
