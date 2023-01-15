package br.com.cleanachitecture.application.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.cleanachitecture.application.adapters.interfaces.UserRepositoryInterface;
import br.com.cleanachitecture.application.entities.UserEntity;
import br.com.cleanachitecture.application.resources.request.UserRequestDTO;
import br.com.cleanachitecture.application.resources.response.UserResponseDTO;
import br.com.cleanachitecture.application.services.interfaces.UserService;

import static br.com.cleanachitecture.application.utils.UserUtils.*;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepositoryInterface repository;
	
	@Override
	public List<UserResponseDTO> listAll() {
		return repository.listAll()
				.stream().map(u -> entityToUserReponseDto(u))
				.collect(Collectors.toList());
	}

	@Override
	public UserResponseDTO findById(Long id) {
		
		UserEntity user = repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		return entityToUserReponseDto(user);
	}

	@Override
	public UserResponseDTO save(UserRequestDTO userRequest) {
		
		UserEntity user = userRequestDtoToEntity(userRequest);
		user.setActive(Boolean.TRUE);
		
		return entityToUserReponseDto(repository.save(user));
	}
	
	@Override
	public UserResponseDTO update(Long id, UserRequestDTO userRequest) {
		
		if(repository.existUserById(id)) {
			
			UserEntity user = repository.findById(id).get();
			
			BeanUtils.copyProperties(userRequestDtoToEntity(userRequest), user, "id", "active");
			
			return entityToUserReponseDto(repository.save(user));
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
