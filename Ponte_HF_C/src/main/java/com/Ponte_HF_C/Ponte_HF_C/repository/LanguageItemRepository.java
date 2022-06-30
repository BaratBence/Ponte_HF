package com.Ponte_HF_C.Ponte_HF_C.repository;

import com.Ponte_HF_C.Ponte_HF_C.model.LanguageItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageItemRepository extends JpaRepository<LanguageItem, Long> {
}
