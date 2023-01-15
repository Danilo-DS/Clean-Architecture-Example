package br.com.cleanachitecture.application.adapters;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import br.com.cleanachitecture.application.adapters.interfaces.UserRepositoryInterface;
import br.com.cleanachitecture.application.adapters.repository.UserRepository;
import br.com.cleanachitecture.application.entities.UserEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserRepositoryImpl implements UserRepositoryInterface {
	
	private final UserRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<UserEntity> listAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<UserEntity> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public UserEntity save(UserEntity user) {
		return repository.save(user);
	}

	@Override
	@Transactional
	public void active(Long id) {
		repository.activeOrInactiveUserById(id, Boolean.TRUE);
	}

	@Override
	@Transactional
	public void inactive(Long id) {
		repository.activeOrInactiveUserById(id, Boolean.FALSE);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existUserById(Long id) {
		return repository.existsById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public boolean existUserByIdAndActive(Long id) {
		return repository.existsUserByIdAndActiveIsTrue(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public boolean existUserByIdAndInactive(Long id) {
		return repository.existsUserByIdAndActiveIsFalse(id);
	}

}
