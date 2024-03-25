package com.vevohub.integrator.database.trello;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "professional_contacts")
@Getter
@Setter
public class ProfessionalContactsEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String profile;

    private String linkedin_profile;

}
