package com.Ponte_HF_C.Ponte_HF_C;

import com.Ponte_HF_C.Ponte_HF_C.model.LanguageItem;
import com.Ponte_HF_C.Ponte_HF_C.model.LanguageProfile;
import com.Ponte_HF_C.Ponte_HF_C.repository.LanguageItemRepository;
import com.Ponte_HF_C.Ponte_HF_C.repository.LanguageProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class LanguageTrainingProcessor {
    @Autowired
    private LanguageProfileRepository languageProfileRepository;
    @Autowired
    private LanguageItemRepository languageItemRepository;
    private LanguageProfiler languageProfiler = new LanguageProfiler();

    public void train() {
        if(languageProfileRepository.findAll().isEmpty()) {
            LanguageProfile profile = languageProfiler.createProfileFromFile("en");
            profile.setLanguageName("English");
            languageProfileRepository.save(profile);
            saveLanguageItems(profile);
            profile = languageProfiler.createProfileFromFile("es");
            profile.setLanguageName("Spanish");
            languageProfileRepository.save(profile);
            saveLanguageItems(profile);
            profile = languageProfiler.createProfileFromFile("hu");
            profile.setLanguageName("Hungarian");
            languageProfileRepository.save(profile);
            saveLanguageItems(profile);
        }
    }

    private void saveLanguageItems(LanguageProfile profile) {
        for(LanguageItem languageItem :profile.getLanguageItems()) {
            languageItemRepository.save(languageItem);
        }
    }
}
