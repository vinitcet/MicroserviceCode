package com.vinit.user.controller;

import com.vinit.user.entity.Users;
import com.vinit.user.service.UserService;
import com.vinit.user.vo.Department;
import com.vinit.user.vo.UserDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/")
    public Users saveUser(@RequestBody Users user) {
        log.info("Saving user :Controller");
        return userService.saveUser(user);
    }

    @GetMapping("/{userId}")
    public UserDetail getUser(@PathVariable Long userId) {
        log.info("Getting user :Controller");
        Users user = userService.getUser(userId);
        UserDetail ud = new UserDetail();
        log.info("Getting department :Controller");
        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/department/" + user.getDepartmentId(), Department.class);
        ud.setUser(user);
        ud.setDepartment(department);
        return ud;
    }

}
