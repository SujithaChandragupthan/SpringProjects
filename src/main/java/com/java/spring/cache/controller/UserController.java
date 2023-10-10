package com.java.spring.cache.controller;

import com.java.spring.cache.entity.User;
import com.java.spring.cache.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/cache")
@CacheConfig(cacheNames = "userCache")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;

    @PostMapping(value = "/createUser")
    public User createUser(@RequestBody User user){
        log.info("UserController.class : createUser method");
        return service.createUser(user);
    }

    @GetMapping(value = "/getAllUsers")
    @Cacheable
    public List<User> getAllUsers() {
        log.info("UserController.class : getAllUsers method");
        return service.getUsers();
    }

    @GetMapping(value = "/getUserById/{id}")
    @Cacheable(key = "#id")
    public User getUser(@PathVariable int id) {
        log.info("UserController.class : getUser method");
        return service.getUser(id);
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    @CacheEvict(key="#id")
    public String deleteUser(@PathVariable int id) {
        log.info("UserController.class : deleteUser method");
        return service.deleteUser(id);
    }

}
