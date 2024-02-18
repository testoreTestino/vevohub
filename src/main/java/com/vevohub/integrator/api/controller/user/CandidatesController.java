package com.vevohub.integrator.api.controller.user;

import com.vevohub.integrator.database.entity.CandidatesEntity;
import com.vevohub.integrator.service.CandidatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
