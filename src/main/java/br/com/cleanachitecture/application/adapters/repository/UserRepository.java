package br.com.cleanachitecture.application.adapters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cleanachitecture.application.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	boolean existsUserByIdAndActiveIsTrue(Long id);
	
	boolean existsUserByIdAndActiveIsFalse(Long id);
	
	@Modifying
	@Query("update TB_USER u set u.active= :value where u.id= :id")
	void activeOrInactiveUserById(@Param(value = "id") Long id, @Param(value = "value") boolean value);
}
