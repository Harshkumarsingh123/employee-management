package com.employee_management.services;

import com.employee_management.dto.request.CreateUserRequest;
import com.employee_management.dto.request.RegisterRequest;
import com.employee_management.dto.response.RegisterResponse;
import com.employee_management.entity.Employee;
import com.employee_management.entity.Role;
import com.employee_management.exception.UserAlreadyExistsException;
import com.employee_management.repository.EmployeeRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServices {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminServices(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterResponse createUser(CreateUserRequest registerRequest){

        if (employeeRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User Already Exists");
        }

        Employee employee=new Employee();
        employee.setName(registerRequest.getName());
        employee.setEmail(registerRequest.getEmail());
        employee.setPhone(registerRequest.getPhone());
        employee.setRole(registerRequest.getRole());
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


    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }

    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }
}
