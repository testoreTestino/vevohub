package com.vevohub.integrator.api.controller.user;

import com.vevohub.integrator.database.entity.CandidatesEntity;
import com.vevohub.integrator.service.CandidatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CandidatesController {

    @Autowired
    private CandidatesService candidatesService;

    @GetMapping("/candidates")
    public List<CandidatesEntity> getCandidates(@RequestParam(required = false) String recruiter, @RequestParam(required = false) String fullNameCandidate, @RequestParam(required = false) Boolean isChecked) {
        if (recruiter != null && !recruiter.isEmpty()) {
            return candidatesService.findByRecruiter(recruiter);
        } else if (fullNameCandidate != null && !fullNameCandidate.isEmpty()) {
            return candidatesService.findByFullNameCandidate(fullNameCandidate);
        } else if (isChecked != null) {
            return candidatesService.findByIsChecked(isChecked);
        }
        return candidatesService.findAll();
    }

    @GetMapping("/positions")
    public List<String> getCandidates(@RequestParam(required = false) String position) {
        if (position != null) {
            return candidatesService.findByPositionName(position);
        }
        return candidatesService.findAllPositions();
    }

    @PostMapping("/create/candidate")
    public ResponseEntity<?> createCandidate(@RequestBody @Validated CandidatesEntity createCandidateRequest) {

        CandidatesEntity submitNewCandidate = candidatesService.createNewCandidate(createCandidateRequest);
        return ResponseEntity.ok(createCandidateRequest);
    }

    @GetMapping("/profiles")
    public List<Object[]> getDistinctProfilesAndNamesByProfilesAndPattern(@RequestParam List<String> profiles, @RequestParam String namePattern) {
        return candidatesService.findDistinctProfilesAndNamesByProfilesAndPattern(profiles, namePattern);
    }

    @GetMapping("/profiles/positions")
    public List<CandidatesEntity> getProfiles(@RequestParam(required = true) String position) {
        return candidatesService.finByProfileContainingPattern(position);
    }
}
