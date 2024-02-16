package com.vevohub.integrator.database.dao;

import com.vevohub.integrator.database.entity.CandidatesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidatesRepository extends JpaRepository<CandidatesEntity, Long> {

    List<CandidatesEntity> findAll();
}
