package com.employee_management.services;

import com.employee_management.dto.request.ProjectRequest;
import com.employee_management.dto.response.ProjectResponse;
import com.employee_management.entity.Project;
import com.employee_management.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }


    public ProjectResponse createDepartment(ProjectRequest projectRequest){

        if(projectRepository.findByProjectName(projectRequest.getProjectName()).isPresent()){
            throw new RuntimeException("Project Already Exists");
        }

        Project project= Project.builder()
                .projectName(projectRequest.getProjectName())
                .build();

        Project savedProject=projectRepository.save(project);

        return new ProjectResponse(
                savedProject.getId(),
                savedProject.getProjectName()
        );
    }

    public void deleteDepartment(Long id){
        projectRepository.deleteById(id);
    }

    public List<Project> getAllDepartment(){
        return projectRepository.findAll();
    }

}
