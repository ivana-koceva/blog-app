package com.app.blog.controller.impl;

import com.app.blog.model.dto.UserDTO;
import com.app.blog.model.dto.UserDetailsDTO;
import com.app.blog.model.exception.AccessDeniedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final RestTemplate restTemplate;
    private static final String URL = "http://localhost:8081/auth";
    private static final String bearerToken = "Bearer ";
    private static final String authHeader = "Authorization";

    @GetMapping("/register")
    public String register(@RequestBody UserDTO user) {
        HttpEntity<UserDTO> request = new HttpEntity<>(user);
        ResponseEntity<String> response = restTemplate
                .exchange(URL+"/register", HttpMethod.POST, request, String.class);
        return response.getBody();
    }

    @GetMapping("/login")
    public String login(@RequestBody UserDTO user) {
        HttpEntity<UserDTO> request = new HttpEntity<>(user);
        ResponseEntity<String> response = restTemplate
                .exchange(URL+"/login", HttpMethod.POST, request, String.class);
        return response.getBody();
    }

    @GetMapping("/validate")
    public String validate(@RequestHeader("Authorization") String token) {
        if(token==null)
            throw new AccessDeniedException();
        HttpHeaders headers = new HttpHeaders();
        if(!token.startsWith(bearerToken)) {
            token = bearerToken + token;
        }
        headers.add(authHeader, token);
        HttpEntity<HttpHeaders> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate
                    .exchange(URL + "/validate", HttpMethod.GET, request, String.class);
        return response.getBody();
    }

    @GetMapping("/details")
    public UserDetailsDTO userDetails(@RequestHeader("Authorization") String token) {
        if(token==null || !token.startsWith(bearerToken))
            throw new AccessDeniedException();
        HttpHeaders headers = new HttpHeaders();
        headers.add(authHeader, token);
        HttpEntity<HttpHeaders> request = new HttpEntity<>(headers);
        ResponseEntity<UserDetailsDTO> response = restTemplate
                .exchange(URL+"/details", HttpMethod.GET, request, UserDetailsDTO.class);
        return response.getBody();
    }

    @DeleteMapping()
    public String deleteUser(@RequestHeader("Authorization") String token, @RequestParam String username) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(authHeader, token);
        HttpEntity<HttpHeaders> request = new HttpEntity<>(headers);
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().contains("ROLE_USER"))
            throw new AccessDeniedException();
        ResponseEntity<String> response = restTemplate
                .exchange(URL+"?username="+username, HttpMethod.DELETE, request, String.class);
        return response.getBody();
    }
}
