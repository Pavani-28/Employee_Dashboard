package com.pavaniprojects.june_edition.controller;

//users enter un&password,who receives this request,not Spring Security, but controller
//POST /auth/login -something like this  (controllers never contain business logic)
//what controllers do receive request->call service(AuthService)->return response


import com.pavaniprojects.june_edition.dto.AuthenticationRequest;
import com.pavaniprojects.june_edition.security.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthenticationRequest request) {

        authenticationService.authenticate(
                request.getUsername(),
                request.getPassword()
        );

        return ResponseEntity.ok("Login Successful");
    }
}
