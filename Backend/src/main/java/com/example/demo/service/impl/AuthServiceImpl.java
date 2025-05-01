package com.example.demo.service.impl;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.SignInRequest;
import com.example.demo.dto.SignInResponse;
import com.example.demo.dto.SignUpRequest;
import com.example.demo.entity.ResponseStatus;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.exception.RoleNotFoundException;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.factory.RoleFactory;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.service.AuthService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleFactory roleFactory;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public ResponseEntity<ApiResponse<?>> signUp(SignUpRequest signUpRequest)
            throws UserAlreadyExistsException, RoleNotFoundException {
        if (userService.existByEmail(signUpRequest.getEmail())) {
            throw new UserAlreadyExistsException("Registration Failed: Provided email already exists. Try sign in or provide another email.");
        }
        if (userService.existByUsername(signUpRequest.getUsername())) {
            throw new UserAlreadyExistsException("Registration Failed: Provided username already exists. Try sign in or provide another username.");
        }

        User user = createUser(signUpRequest);
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.builder()
                        .status(String.valueOf(ResponseStatus.SUCCESS))
                        .message("User account has been successfully created!")
                        .build()
        );
    }

    @Override
    public ResponseEntity<ApiResponse<?>> signIn(SignInRequest signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        SignInResponse signInResponseDto = SignInResponse.builder()
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .id(userDetails.getId())
                .token(jwt)
                .type("Bearer")
                .roles(roles)
                .build();

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .status(String.valueOf(ResponseStatus.SUCCESS))
                        .message("Sign in successfull!")
                        .response(signInResponseDto)
                        .build()
        );
    }

    private User createUser(SignUpRequest signUpRequest) throws RoleNotFoundException {
        return User.builder()
                .email(signUpRequest.getEmail())
                .username(signUpRequest.getUsername())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .enabled(true)
                .roles(determineRoles(signUpRequest.getRoles()))
                .build();
    }

    private Set<Role> determineRoles(Set<String> strRoles) throws RoleNotFoundException {
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            roles.add(roleFactory.getInstance("user"));
        } else {
            for (String role : strRoles) {
                roles.add(roleFactory.getInstance(role));
            }
        }
        return roles;
    }
}
