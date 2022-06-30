package com.Ponte_HF_C.Ponte_HF_C.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class LanguageItem {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="language_profile_id")
    private LanguageProfile profile;
    @NotNull
    private String triplet;

    public LanguageItem() {}

    public LanguageItem(LanguageProfile profile, String triplet) {
        this.profile = profile;
        this.triplet = triplet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LanguageItem that = (LanguageItem) o;
        return Objects.equals(triplet, that.triplet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(triplet);
    }

    public Long getId() {
        return id;
    }

    public String getTriplet() {
        return triplet;
    }

    public LanguageProfile getProfile() {
        return profile;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTriplet(String triplet) {
        this.triplet = triplet;
    }

    public void setProfile(LanguageProfile profile) {
        this.profile = profile;
    }


}
