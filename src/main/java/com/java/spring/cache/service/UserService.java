package com.java.spring.cache.service;

import com.java.spring.cache.entity.User;
import com.java.spring.cache.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public User createUser(User user){
        log.info("UserService.class : createUser method");
        return userRepository.save(user);
    }


    public List<User> getUsers() {
        log.info("UserService.class : getUsers method");
        return userRepository.findAll();
    }

    public User getUser(int id) {
        log.info("UserService.class : getUser method");
        return userRepository.findById(id).get();
    }

    public String deleteUser(int id) {
        log.info("UserService.class : deleteUser method");
        User deleteUser = userRepository.findById(id).get();
        userRepository.delete(deleteUser);
        return "Id deleted";
    }

}
