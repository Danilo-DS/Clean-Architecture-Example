package br.com.cleanachitecture.application.adapters.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.cleanachitecture.application.entities.UserEntity;

public interface UserRepositoryInterface {
	
	List<UserEntity> listAll();
	
	Optional<UserEntity> findById(Long id);
	
	UserEntity save(UserEntity user);
	
	void active(Long id);
	
	void inactive(Long id);
	
	boolean existUserById(Long id);
	
	boolean existUserByIdAndActive(Long id);
	
	boolean existUserByIdAndInactive(Long id);
	
}
