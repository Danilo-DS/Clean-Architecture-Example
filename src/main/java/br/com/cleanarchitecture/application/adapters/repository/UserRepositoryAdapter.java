package br.com.cleanarchitecture.application.adapters.repository;

import java.util.List;
import java.util.Optional;

import br.com.cleanarchitecture.application.entities.UserEntity;

public interface UserRepositoryAdapter {
	
	List<UserEntity> listAll();
	
	Optional<UserEntity> findById(Long id);
	
	UserEntity save(UserEntity user);
	
	void active(Long id);
	
	void inactive(Long id);
	
	boolean existUserById(Long id);
	
	boolean existUserByIdAndActive(Long id);
	
	boolean existUserByIdAndInactive(Long id);
	
}
