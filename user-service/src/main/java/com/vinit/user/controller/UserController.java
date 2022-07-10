package com.vinit.user.controller;

import java.util.Date;

import com.vinit.user.entity.Users;
import com.vinit.user.service.UserService;
import com.vinit.user.vo.Department;
import com.vinit.user.vo.UserDetail;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController
{
    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    private static final String DEPARTMENT_SERVICE = "departmentService";

    @PostMapping("/")
    public Users saveUser( @RequestBody Users user )
    {
        log.info( "Saving user :Controller" );
        return userService.saveUser( user );
    }

    int count=0;

    @GetMapping("/{userId}")
   // @CircuitBreaker(name = DEPARTMENT_SERVICE, fallbackMethod ="getUserFallback"  )
    //@Retry( name = DEPARTMENT_SERVICE )
    @RateLimiter(name = DEPARTMENT_SERVICE  )
    public UserDetail getUser( @PathVariable Long userId )
    {
        log.info( "Getting user :Controller" );
        Users user = userService.getUser( userId );
        UserDetail ud = new UserDetail();
        log.info( "Getting department :Controller" );
        if(count>0){
            log.info( "Retrhy method is called "+ count++ +"times at"+new Date() );
        }
        Department department =
            restTemplate.getForObject( "http://DEPARTMENT-SERVICE/department/" + user.getDepartmentId(),
                                       Department.class );
        ud.setUser( user );
        ud.setDepartment( department );
        return ud;
    }

    public UserDetail getUserFallback( Long userId , Exception e )
    {
        return new UserDetail();
    }

}
