package org.capston.project.epam.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.capston.project.epam.dto.accounts.UserSignupDto;
import org.capston.project.epam.security.AuthenticationRequest;
import org.capston.project.epam.security.JwtToken;
import org.capston.project.epam.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/user")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<JwtToken> login(@RequestBody @Valid @NotNull AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }


    @PostMapping("/signup")
    public ResponseEntity<JwtToken> signup(@RequestBody @NotNull UserSignupDto userSignupDto) {
        return ResponseEntity.ok(authenticationService.signup(userSignupDto));
    }
}
