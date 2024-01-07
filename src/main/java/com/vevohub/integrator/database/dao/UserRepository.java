package com.vevohub.integrator.database.dao;

import com.vevohub.integrator.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
        UserEntity findByUsername(String username);
}
