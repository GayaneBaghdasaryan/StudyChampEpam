package org.capston.project.epam.service;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.capston.project.epam.dto.accounts.UserSignupDto;
import org.capston.project.epam.entity.User;
import org.capston.project.epam.repository.UserRepository;
import org.capston.project.epam.security.AuthUser;
import org.capston.project.epam.security.AuthenticationRequest;
import org.capston.project.epam.security.JwtToken;
import org.capston.project.epam.security.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    @Transactional
    public JwtToken authenticate(AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(EntityNotFoundException::new);

        return jwtTokenUtil.generateToken(authentication.getName(), AuthUser
                .builder()
                .id(user.getId())
                .username(user.getEmail())
                .authorities(authentication.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toSet()))
                .build());
    }

    @Transactional
    @SneakyThrows
    public JwtToken signup(UserSignupDto userSignupDto) {
        String username = userSignupDto.getUsername();
        var optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            throw new Exception("User is already signed up");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        var user = userRepository.save(User.builder()
                .username(username)
                .password(passwordEncoder.encode(userSignupDto.getPassword()))
                .email(userSignupDto.getEmail())
                .firstName(userSignupDto.getFirstName())
                .lastName(userSignupDto.getLastName())
                .build());

        return jwtTokenUtil.generateToken(user.getUsername(), new AuthUser(user));
    }
}
