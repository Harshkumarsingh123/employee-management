package com.employee_management.services;

import com.employee_management.dto.request.LoginRequest;
import com.employee_management.dto.request.RegisterRequest;
import com.employee_management.dto.response.LoginResponse;
import com.employee_management.dto.response.RegisterResponse;
import com.employee_management.entity.Employee;
import com.employee_management.entity.Role;
import com.employee_management.exception.UserAlreadyExistsException;
import com.employee_management.exception.UserNotExistsException;
import com.employee_management.repository.EmployeeRepository;
import com.employee_management.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public RegisterResponse createEmployee(RegisterRequest registerRequest){

        if (employeeRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User Already Exists");
        }

        Employee employee=new Employee();
        employee.setName(registerRequest.getName());
        employee.setEmail(registerRequest.getEmail());
        employee.setPhone(registerRequest.getPhone());
        employee.setRole(Role.EMPLOYEE);
        employee.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        Employee savedEmployee=employeeRepository.save(employee);

        RegisterResponse response= RegisterResponse.builder()
                .id(savedEmployee.getId())
                .name(savedEmployee.getName())
                .email(savedEmployee.getEmail())
                .phone(savedEmployee.getPhone())
                .build();

        return response;
    }

    public LoginResponse employeeLogin(LoginRequest loginRequest){

        Employee employee = employeeRepository
                .findByEmail(loginRequest.getEmail())
                .orElseThrow(() ->
                        new UserNotExistsException("Invalid Credentials"));

        if(!passwordEncoder.matches(loginRequest.getPassword(), employee.getPassword())){
            throw new UserNotExistsException("Invalid Credentials");
        }

        String token=jwtService.generateToken(employee);
        return new LoginResponse(token);
    }
}
