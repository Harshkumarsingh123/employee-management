package com.employee_management.repository;

import com.employee_management.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository  extends JpaRepository<Project,Long> {


    Optional<Project> findByProjectName(String name);
}
