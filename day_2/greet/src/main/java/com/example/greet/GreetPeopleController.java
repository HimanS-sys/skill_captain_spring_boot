package com.example.greet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetPeopleController {
    @Autowired
    private GreetingService greetingService;
    public GreetPeopleController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }
    @GetMapping("/")
    public String helloWorld() {
        return "Hello World!";
    }
    @GetMapping("/greet/{name}")
    public ResponseEntity<String> greetPerson(@PathVariable String name) {
        if (name == null || name.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: name cannot be empty!");
        }
        String greeting = greetingService.generateGreeting(name, "en");
        return ResponseEntity.ok(greeting);
    }

    @GetMapping("/greet/{name}/{language}")
    public ResponseEntity<String> greetPersonLanguage(
            @PathVariable String name,
            @PathVariable String language
    ) {
        if (name == null || name.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: name cannot be empty!");
        }
        String greeting = greetingService.generateGreeting(name, language);
        return ResponseEntity.ok(greeting);
    }

    @PostMapping("/greet")
    public ResponseEntity<String> greetByJson(@RequestBody GreetingRequest request) {
        String name = request.getName();
        if (name == null || name.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: name cannot be empty!");
        }
        String responseMessage = greetingService.generateResponseMessage(name);
        return ResponseEntity.ok(responseMessage);
    }
}
