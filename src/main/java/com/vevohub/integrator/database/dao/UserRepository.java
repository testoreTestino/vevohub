package com.vevohub.integrator.database.dao;

import com.vevohub.integrator.database.dto.UserEmailUserNameDto;
import com.vevohub.integrator.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    UserEntity findByEmail(String email);


    @Query("SELECT new com.vevohub.integrator.database.dto.UserEmailUserNameDto(u.email, u.username) FROM UserEntity u WHERE u.email = :email")
    Optional<UserEmailUserNameDto> findUserByEmail(String email);

}
