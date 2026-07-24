package com.employee_management.controller;

import com.employee_management.dto.request.CreateUserRequest;
import com.employee_management.dto.request.DepartmentRequest;
import com.employee_management.dto.response.DepartmentResponse;
import com.employee_management.dto.response.RegisterResponse;
import com.employee_management.entity.Department;
import com.employee_management.entity.Employee;
import com.employee_management.services.AdminServices;
import com.employee_management.services.DepartmentServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/")
public class AdminController {

    private final AdminServices adminServices;
    private final DepartmentServices departmentServices;


    public AdminController(AdminServices adminServices, DepartmentServices departmentServices) {
        this.adminServices = adminServices;
        this.departmentServices = departmentServices;
    }

    @PostMapping("/create-employee")
    public ResponseEntity<RegisterResponse> createUser(@Valid @RequestBody CreateUserRequest registerRequest){
        return new ResponseEntity<>(adminServices.createUser(registerRequest), HttpStatus.CREATED);
    }


    @GetMapping("/getAllEmployee")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        return new ResponseEntity<>(adminServices.getAllEmployee(),HttpStatus.FOUND);
    }

    @DeleteMapping("/delete-Employee/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        adminServices.deleteEmployee(id);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/create-department")
    public ResponseEntity<DepartmentResponse> createResponse(@RequestBody DepartmentRequest departmentRequest){
        return new ResponseEntity<>(departmentServices.createDepartment(departmentRequest),HttpStatus.CREATED);
    }

    @GetMapping("/getAllDepartment")
    public ResponseEntity<List<Department>> getAllDepartment(){
        return new ResponseEntity<>(departmentServices.getAllDepartment(),HttpStatus.FOUND);
    }

    @DeleteMapping("/delete-Department/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id){
        departmentServices.deleteDepartment(id);
        return ResponseEntity.accepted().build();
    }
}
