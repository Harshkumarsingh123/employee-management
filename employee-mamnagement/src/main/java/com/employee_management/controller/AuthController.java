package com.employee_management.controller;

import com.employee_management.dto.request.LoginRequest;
import com.employee_management.dto.request.RegisterRequest;
import com.employee_management.dto.response.LoginResponse;
import com.employee_management.dto.response.RegisterResponse;
import com.employee_management.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth/")
public class AuthController {

    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<RegisterResponse> createUser(@Valid @RequestBody RegisterRequest registerRequest){
        return new ResponseEntity<>(authService.createEmployee(registerRequest), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request){

        return ResponseEntity.ok(authService.employeeLogin(request));
    }
}
