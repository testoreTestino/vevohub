package com.vevohub.integrator.database.dao;

import com.vevohub.integrator.database.entity.CandidatesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CandidatesRepository extends JpaRepository<CandidatesEntity, Long> {


    /*
    The name of the method should reflect the entity if name of entry inside the entity is candidate ,
     name of the method needs to be findByName  https://www.baeldung.com/spring-data-jpa-exception-no-property-found-for-type#:~:text=The%20stack%20trace%20%E2%80%9CNo%20property,of%20the%20derived%20query%20methods.
     */
    /*

    Methods :

     https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
     */

    List<CandidatesEntity> findAll();

    List<CandidatesEntity> findByRecruiter(String recruiter);
    //TODO: Modify the column fullNameCandidate

    List<CandidatesEntity> findByFullNameCandidateStartingWith(String fullNameCandidate);


    List<CandidatesEntity> findByIsChecked(Boolean isChecked);

    @Query("SELECT DISTINCT c.profile FROM CandidatesEntity c")
    List<String> findAllDistinctProfiles();

    @Query("SELECT DISTINCT c.profile FROM CandidatesEntity c WHERE c.profile LIKE %:pattern%")
    List<String> findDistinctProfilesByPattern(@Param("pattern") String pattern);
}
