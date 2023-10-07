package com.example.greet;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GreetingService {

    private Map<String, String> languageMap;

    public GreetingService() {
        this.languageMap = new HashMap<>();
        this.languageMap.put("en", "Hello");
        this.languageMap.put("hnd", "Namaste");
        this.languageMap.put("es", "Hola");
        this.languageMap.put("jp", "Kon'nichiwa");
    }

    public String generateGreeting(String name, String language) {
        // Use languageMap to provide specific greetings
        language = (language == null || language.isBlank()) ? "en" : language;
        String greeting = this.languageMap.getOrDefault(language, this.languageMap.get("en"));
        return greeting + ", " + name + "!";
    }

    public String generateResponseMessage(String name) {
        return "Hi " + name + ", your response is submitted!";
    }
}
