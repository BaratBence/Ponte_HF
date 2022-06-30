package com.Ponte_HF_C.Ponte_HF_C.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

//Class containing a single profile
@Entity
@Table(name = "languageProfile")
public class LanguageProfile {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String languageName;

    @OneToMany(mappedBy = "profile")
    private List<LanguageItem> languageItems = new ArrayList<>();

    public LanguageProfile() {
    }

    public LanguageProfile(String languageName, List<LanguageItem> languageItems) {
        this.languageName = languageName;
        this.languageItems = languageItems;
    }

    public Long getId() {
        return id;
    }
    public String getLanguageName() {
        return languageName;
    }
    public List<LanguageItem> getLanguageItems() {
        return languageItems;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public void setLanguageItems(ArrayList<LanguageItem> languageItems) {
        this.languageItems = languageItems;
    }

    //creates a score based on how close the triplets are
    public int calculateScore(LanguageProfile languageProfile) {
        int score = 0;
        for(LanguageItem triplet : languageItems) {
            int index = languageItems.indexOf(triplet);
            if(index == -1) {
                score += 20;
            }
            else {
                int sampleIndex = languageProfile.languageItems.indexOf(triplet);
                score += Math.abs(index - sampleIndex);
            }
        }
        return score;
    }
}
