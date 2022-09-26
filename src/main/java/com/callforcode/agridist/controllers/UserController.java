package com.callforcode.agridist.controllers;

import com.callforcode.agridist.entities.User;
import com.callforcode.agridist.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public List<User> getAllUser( ) throws InterruptedException, ExecutionException{
        return userService.getUsers();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable String id ) throws InterruptedException, ExecutionException{
        return userService.getUserDetails(id);
    }

    @PostMapping("/user")
    public String createUser(@RequestBody User user ) throws InterruptedException, ExecutionException {
        return userService.saveUserDetails(user);
    }

    @PutMapping("/user/{id}")
    public String updateUser(@RequestBody User user , @PathVariable String id ) throws InterruptedException, ExecutionException {
        return userService.updateUserDetails(user, id);
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable String id){
        return userService.deleteUser(id);
    }
}