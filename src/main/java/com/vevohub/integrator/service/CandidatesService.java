package com.vevohub.integrator.service;

import com.vevohub.integrator.database.dao.CandidatesRepository;
import com.vevohub.integrator.database.entity.CandidatesEntity;
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

    public List<CandidatesEntity> findByNameContainingIgnoreCase(String recruiter) {
        return findByNameContainingIgnoreCase(recruiter);
    }

    public List<CandidatesEntity> findByRecruiter(String recruiter) {
        return candidatesRepository.findByRecruiter(recruiter);
    }

    public List<CandidatesEntity> findByFullNameCandidate(String fullNameCandidate) {
        String searchPattern = fullNameCandidate.length() > 4 ? fullNameCandidate.substring(0, 4) : fullNameCandidate;
        return candidatesRepository.findByFullNameCandidateStartingWith(searchPattern);
    }

    public List<CandidatesEntity> findByIsChecked(Boolean isChecked) {
//        String searchPattern = fullNameCandidate.length() > 4 ? fullNameCandidate.substring(0, 4) : fullNameCandidate;
        return candidatesRepository.findByIsChecked(isChecked);
    }

    public List<String> findByPositionName(String position) {
        return candidatesRepository.findDistinctProfilesByPattern(position);
    }

    public List<String> findAllPositions() {
        return candidatesRepository.findAllDistinctProfiles();
    }
}
