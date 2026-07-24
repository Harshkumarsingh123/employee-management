package com.employee_management.services;

import com.employee_management.dto.request.DepartmentRequest;
import com.employee_management.dto.response.DepartmentResponse;
import com.employee_management.entity.Department;
import com.employee_management.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServices {

    private final DepartmentRepository departmentRepository;

    public DepartmentServices(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    public DepartmentResponse createDepartment(DepartmentRequest departmentRequest){

        if(departmentRepository.findByDepartmentName(departmentRequest.getDepartmentName()).isPresent()){
            throw new RuntimeException("Department Already Exists");
        }

        Department department= Department.builder()
                        .departmentName(departmentRequest.getDepartmentName())
                        .build();

        Department savedDepartment=departmentRepository.save(department);

        return new DepartmentResponse(
                savedDepartment.getId(),
                savedDepartment.getDepartmentName()
        );
    }

    public void deleteDepartment(Long id){
        departmentRepository.deleteById(id);
    }

    public List<Department> getAllDepartment(){
        return departmentRepository.findAll();
    }
}
