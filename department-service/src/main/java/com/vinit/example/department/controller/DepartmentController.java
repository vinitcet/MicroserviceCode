package com.vinit.example.department.controller;

import com.vinit.example.department.entity.Department;
import com.vinit.example.department.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("department")
@Slf4j
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/")
    public Department saveDepartment(@RequestBody Department department) {
        log.info("Saving department : Controller");
        return departmentService.saveDepartment(department);

    }

    @GetMapping("/{departmentId}")
    public Department findDepartmentById(@PathVariable Long departmentId) {
        log.info("Finding department : Controller");
        return departmentService.findDepartmentById(departmentId).get();
    }
}
