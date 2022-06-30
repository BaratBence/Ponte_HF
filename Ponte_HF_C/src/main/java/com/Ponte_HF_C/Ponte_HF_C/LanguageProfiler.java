package com.Ponte_HF_C.Ponte_HF_C;

import com.Ponte_HF_C.Ponte_HF_C.model.LanguageItem;
import com.Ponte_HF_C.Ponte_HF_C.model.LanguageProfile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

//Class to clean and create a language profile
public class LanguageProfiler {

    ArrayList<String> triplets = new ArrayList<>();

    //Creates a profile from file, cleans it first line by line
    public LanguageProfile createProfileFromFile(String languageName) {
        Path rootDir = Paths.get(".").normalize().toAbsolutePath();
        try {
            InputStream inputStream = Files.newInputStream(Paths.get(rootDir.toString() + "\\src\\" + languageName + "\\content.txt"));
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = br.readLine()) != null) {
                line = cleanLine(line);
                createTriplets(line);
            }
            return createLanguageDescription();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Creates profile from given text
    public LanguageProfile createProfileFromString(String text) {
        text = cleanLine(text);
        createTriplets(text);
        return createLanguageDescription();
    }

    //Cleans the give text from extra spaces or unnecessary characters
    private String cleanLine(String line) {
        line = line.toLowerCase();
        line  = line.replaceAll("\\s{2,}", " ").trim();
        return line.replaceAll("[\\t\\r\\n\".!?:,;_-]", "");
    }

    //creates the triplets from the cleaned text
    private void createTriplets(String line) {
        for(int i=0; i<line.length()-2; i++) {
            triplets.add(line.substring(i,i+3));
        }
    }

    //Creates the profile with the 100 most used triplets
    private LanguageProfile createLanguageDescription() {
        Map<String, Long> counts = triplets.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        List<Map.Entry<String, Long> > list = new LinkedList<>(counts.entrySet());
        list.sort(Map.Entry.comparingByValue());
        LanguageProfile profile = new LanguageProfile();
        if(list.size()<100) {
            for (int i = list.size(); i > 0; i--) {
                profile.getLanguageItems().add(new LanguageItem(profile, list.get(i - 1).getKey()));
            }
        }
        else {
            for (int i = list.size(); i > list.size() - 100; i--) {
                profile.getLanguageItems().add(new LanguageItem(profile, list.get(i - 1).getKey()));
            }
        }
        triplets = new ArrayList<>();
        return profile;
    }
}
