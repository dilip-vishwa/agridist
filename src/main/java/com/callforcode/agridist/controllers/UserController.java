package com.callforcode.agridist.controllers;

import com.callforcode.agridist.entities.User;
import com.callforcode.agridist.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUser( ) throws InterruptedException, ExecutionException{
        List<User> users = userService.getUsers();
        if(users.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.of(Optional.of(users));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable String id ) throws InterruptedException, ExecutionException {
        Optional<User> user = userService.getUserDetails(id);
        if (!user.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(user));
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user ) throws InterruptedException, ExecutionException {
        try{
            User user1 = userService.saveUserDetails(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user1);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user , @PathVariable String id ) throws InterruptedException, ExecutionException {
        try {
            userService.updateUserDetails(user, id);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        try {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}