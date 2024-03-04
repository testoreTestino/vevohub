package com.vevohub.integrator.api.controller.trello;

import com.vevohub.integrator.database.trello.ProfessionalContactsEntity;
import com.vevohub.integrator.service.trello.ProfessionalContactsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ContactsController {

    @Autowired
    private ProfessionalContactsService professionalContactsService;

    @GetMapping("/professionals")
    public List<ProfessionalContactsEntity> getProfessionals() {
        return professionalContactsService.findAll();
    }
}
