package com.vevohub.integrator.service.trello;


import com.vevohub.integrator.database.dao.ContactsRepository;
import com.vevohub.integrator.database.trello.ProfessionalContactsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionalContactsService {

    @Autowired
    private ContactsRepository contactsRepository;

    public List<ProfessionalContactsEntity> findAll(){
        return contactsRepository.findAll();
    }
}
