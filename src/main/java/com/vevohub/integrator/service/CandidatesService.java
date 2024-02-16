package com.vevohub.integrator.service;

import com.vevohub.integrator.database.dao.CandidatesRepository;
import com.vevohub.integrator.database.entity.CandidatesEntity;
import com.vevohub.integrator.database.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatesService {

    @Autowired
    private CandidatesRepository candidatesRepository;

    public List<CandidatesEntity> findAll() {
        return candidatesRepository.findAll();
    }

//    public List<CandidatesEntity> findByNameContainingIgnoreCase(String name) {
//        return candidatesRepository.findByNameContainingIgnoreCase(name);
//    }

}
