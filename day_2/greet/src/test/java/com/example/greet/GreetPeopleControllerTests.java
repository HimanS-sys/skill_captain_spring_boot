package com.example.greet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;

public class GreetPeopleControllerTests {

    @InjectMocks
    private GreetPeopleController controller;

    @Mock
    private GreetingService greetingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        Mockito.when(greetingService.generateGreeting(eq("John"), eq("en"))).thenReturn("Hello, John!");
        Mockito.when(greetingService.generateGreeting(eq(""), anyString())).thenReturn("Error: name cannot be empty!");
        Mockito.when(greetingService.generateGreeting(eq("Maria"), eq("es"))).thenReturn("Hola, Maria!");
        Mockito.when(greetingService.generateGreeting(eq("Luis"), anyString())).thenReturn("Hello, Luis!");
        Mockito.when(greetingService.generateGreeting(eq("Takashi"), anyString())).thenReturn("Hello, Takashi!");
        Mockito.when(greetingService.generateResponseMessage(eq("Jarad"))).thenReturn("Hi Jarad, your response is submitted!");
        Mockito.when(greetingService.generateResponseMessage(eq(""))).thenReturn("Error: name cannot be empty!");
    }

    @Test
    public void testGreetByJson_ValidInput() {
        ResponseEntity<String> response = controller.greetByJson(new GreetingRequest("Jarad"));
        assertResponse(response, HttpStatus.OK, "Hi Jarad, your response is submitted!");
    }

    @Test
    public void testGreatByJson_InvalidInput() {
        ResponseEntity<String> response = controller.greetByJson(new GreetingRequest(""));
        assertResponse(response, HttpStatus.BAD_REQUEST, "Error: name cannot be empty!");
    }

    @Test
    public void testGreetPerson_ValidInput() {
        ResponseEntity<String> response = controller.greetPerson("John");
        assertResponse(response, HttpStatus.OK, "Hello, John!");
    }

    @Test
    public void testGreetPerson_EmptyName() {
        ResponseEntity<String> response = controller.greetPerson("");
        assertResponse(response, HttpStatus.BAD_REQUEST, "Error: name cannot be empty!");
    }

    @Test
    public void testGreetPersonLanguage_ValidInput() {
        ResponseEntity<String> response = controller.greetPersonLanguage("Maria", "es");
        assertResponse(response, HttpStatus.OK, "Hola, Maria!");
    }

    @Test
    public void testGreetPersonLanguage_InvalidLanguage() {
        ResponseEntity<String> response = controller.greetPersonLanguage("Luis", "de");
        assertResponse(response, HttpStatus.OK, "Hello, Luis!"); // Default to English for invalid language
    }

    @Test
    public void testGreetPersonLanguage_EmptyName() {
        ResponseEntity<String> response = controller.greetPersonLanguage("", "jp");
        assertResponse(response, HttpStatus.BAD_REQUEST, "Error: name cannot be empty!");
    }

    @Test
    public void testGreetPersonLanguage_EmptyLanguage() {
        ResponseEntity<String> response = controller.greetPersonLanguage("Takashi", "");
        assertResponse(response, HttpStatus.OK, "Hello, Takashi!"); // Default to English for empty language
    }

    private void assertResponse(ResponseEntity<String> actualResponse, HttpStatus expectedStatus, String expectedBody) {
        HttpStatus actualStatus = (HttpStatus) actualResponse.getStatusCode();
        String actualBody = actualResponse.getBody();

        assertEquals(expectedStatus, actualStatus);

        if (expectedBody != null) {
            assertEquals(expectedBody, actualBody);
        }
    }
}