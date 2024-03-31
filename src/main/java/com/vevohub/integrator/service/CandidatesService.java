package com.vevohub.integrator.service;

import com.vevohub.integrator.database.dao.CandidatesRepository;
import com.vevohub.integrator.database.entity.CandidatesEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import kong.unirest.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidatesService {

    @Autowired
    private CandidatesRepository candidatesRepository;

    @Autowired
    private EntityManager entityManager;

    private CandidatesEntity candidatesEntity;

    public List<CandidatesEntity> findAll() {
        return candidatesRepository.findAll();
    }

    public List<CandidatesEntity> findByFullNameCandidate(String fullNameCandidate) {
        String searchPattern = fullNameCandidate.length() > 4 ? fullNameCandidate.substring(0, 4) : fullNameCandidate;
        return candidatesRepository.findByFullNameCandidateStartingWith(searchPattern);
    }

    public List<String> findByPositionName(String position) {
        return candidatesRepository.findDistinctProfilesByPattern(position);
    }

    public List<String> findAllPositions() {
        return candidatesRepository.findAllDistinctProfiles();
    }

    public CandidatesEntity createNewCandidate(CandidatesEntity createCandidateRequest) {
        return candidatesRepository.save(createCandidateRequest);
    }

    //TRELLO
    public CandidatesEntity transformCardToCandidateEntity(JSONObject card) {
        CandidatesEntity candidate = new CandidatesEntity();
        // Parse the card description or other fields to set entity properties

        candidate.setFullNameCandidate(card.getString("name"));
        // Set other fields based on your card structure and entity fields
        // Example: candidate.setLocationCity(parseLocationFromDescription(card.getString("desc")));
        // Remember to implement parsing logic based on how your data is structured in Trello cards

        return candidate;
    }

    public List<CandidatesEntity> findDistinctProfilesAndNamesByProfilesAndPattern(List<String> profiles, String namePattern) {
        return candidatesRepository.findByProfilesAndNamePattern(profiles, namePattern);
    }

    public List<CandidatesEntity> finByProfileContainingPattern(List<String> profilePattern) {
        return candidatesRepository.findByProfileContaining(profilePattern);
    }

    public void deleteCandidateById(Long id) {
        candidatesRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return candidatesRepository.existsById(id);
    }

    public Optional<CandidatesEntity> findById(Long id) {
        return candidatesRepository.findById(id);
    }
}
