package com.Ponte_HF_C.Ponte_HF_C.controller;

import com.Ponte_HF_C.Ponte_HF_C.LanguageProfiler;
import com.Ponte_HF_C.Ponte_HF_C.model.LanguageProfile;
import com.Ponte_HF_C.Ponte_HF_C.repository.LanguageProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/language")
public class LanguageController {
    @Autowired
    private LanguageProfileRepository languageProfileRepository;
    LanguageProfiler languageProfiler = new LanguageProfiler();
    //Endpoint requiring a singe text and will reply with the language
    @PostMapping
    public ResponseEntity<String> identifyLanguage(@RequestBody String text)  {
        LanguageProfile profile  = languageProfiler.createProfileFromString(text);
        int minScore = -1;
        String language = "";
        //Check for each learned language
        ArrayList<LanguageProfile> profiles = (ArrayList<LanguageProfile>) languageProfileRepository.findAll();
        for (LanguageProfile languages : profiles) {
            int score = languages.calculateScore(profile);
            if(score < minScore || minScore == -1) {
                minScore = score;
                language = languages.getLanguageName();
            }
        }
        return ResponseEntity.ok(language);
    }
}
