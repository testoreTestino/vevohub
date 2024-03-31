package com.vevohub.integrator.api.controller.user;

import com.vevohub.integrator.database.entity.CandidatesEntity;
import com.vevohub.integrator.service.CandidatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CandidatesController {

    @Autowired
    private CandidatesService candidatesService;

    @GetMapping("/candidates")
    public ResponseEntity<?> getCandidates(
            @RequestParam(required = false) String fullNameCandidate,
            @RequestParam(required = false) List<String> profiles,
            @RequestParam(required = false) String namePattern) {

        // Check for distinct profiles and names by profiles and pattern
        if (profiles != null && !profiles.isEmpty() && namePattern != null && !namePattern.isEmpty()) {
            return ResponseEntity.ok(candidatesService.findDistinctProfilesAndNamesByProfilesAndPattern(profiles, namePattern));
        }

        if (profiles != null && !profiles.isEmpty() && (fullNameCandidate == null || fullNameCandidate.isEmpty()) && (namePattern == null || namePattern.isEmpty())) {
            return ResponseEntity.ok(candidatesService.finByProfileContainingPattern(profiles));
        }

        if (fullNameCandidate != null && !fullNameCandidate.isEmpty()) {
            return ResponseEntity.ok(candidatesService.findByFullNameCandidate(fullNameCandidate));
        }

        return ResponseEntity.ok(candidatesService.findAll());
    }

    @PostMapping("/create/candidate")
    public ResponseEntity<?> createCandidate(@RequestBody @Validated CandidatesEntity createCandidateRequest) {

        CandidatesEntity submitNewCandidate = candidatesService.createNewCandidate(createCandidateRequest);
        return ResponseEntity.ok(createCandidateRequest);
    }

    @GetMapping("/profiles")
    public List<CandidatesEntity> getDistinctProfilesAndNamesByProfilesAndPattern(@RequestParam List<String> profiles, @RequestParam String namePattern) {
        return candidatesService.findDistinctProfilesAndNamesByProfilesAndPattern(profiles, namePattern);
    }

    @GetMapping("/candidates/positions")
    public List<String> getProfiles() {
        return candidatesService.findAllPositions();
    }

    @DeleteMapping("/candidates/{id}")
    public ResponseEntity<?> deleteCandidateById(@PathVariable Long id) {
        // Check if the candidate exists before attempting to delete to provide a clearer error message if not
        if (!candidatesService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        candidatesService.deleteCandidateById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/candidates/{id}")
    public Optional<CandidatesEntity> findCandidateById(@PathVariable Long id) {

        return candidatesService.findById(id);

    }
}
