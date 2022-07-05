package com.vinit.example.department.service;

import com.vinit.example.department.entity.Department;
import com.vinit.example.department.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@Slf4j
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;


    public Department saveDepartment(Department department) {
        log.info("Saving department : Service");
        return departmentRepository.save(department);
    }

    public Optional<Department> findDepartmentById(Long departmentId) {
        log.info("Finding department : Service");
        return departmentRepository.findById(departmentId);
    }
}
