package com.employee_management.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
public class DepartmentRequest {

    @NotBlank
    private String departmentName;
}
