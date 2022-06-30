package com.Ponte_HF_C.Ponte_HF_C.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Provides functions to handle LanguageProfiles into MySQL db
@Repository
public interface LanguageProfileRepository extends JpaRepository<com.Ponte_HF_C.Ponte_HF_C.model.LanguageProfile, Long> {

}
