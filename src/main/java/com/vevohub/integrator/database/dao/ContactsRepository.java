package com.vevohub.integrator.database.dao;

import com.vevohub.integrator.database.trello.ProfessionalContactsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactsRepository extends JpaRepository<ProfessionalContactsEntity, Long> {

    List<ProfessionalContactsEntity> findAll();
}
