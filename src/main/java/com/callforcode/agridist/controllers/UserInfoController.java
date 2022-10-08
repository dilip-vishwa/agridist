package com.callforcode.agridist.controllers;

import com.callforcode.agridist.entities.User;
import com.callforcode.agridist.entities.UserInfo;
import com.callforcode.agridist.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;

    @GetMapping("/userinfo")
    public ResponseEntity<List<UserInfo>> getAllUserInfo( ) throws InterruptedException, ExecutionException{
        List<UserInfo> userInfos = userInfoService.getUserInfos();
        if(userInfos.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.of(Optional.of(userInfos));
    }

    @GetMapping("/userinfo/{id}")
    public ResponseEntity<Optional<UserInfo>> getUserInfo(@PathVariable String id ) throws InterruptedException, ExecutionException{
        Optional<UserInfo> userInfo = userInfoService.getUserInfoDetails(id);
        if (userInfo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(userInfo));
    }

    @PostMapping("/userinfo")
    public ResponseEntity<UserInfo> createUserInfo(@RequestBody UserInfo userInfo ) throws InterruptedException, ExecutionException {
        try{
            UserInfo user1 = userInfoService.saveUserInfoDetails(userInfo);
            return ResponseEntity.status(HttpStatus.CREATED).body(user1);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/userinfo/{id}")
    public ResponseEntity<UserInfo> updateUserInfo(@RequestBody UserInfo userInfo , @PathVariable String id ) throws InterruptedException, ExecutionException {
        try {
            userInfoService.updateUserInfoDetails(userInfo, id);
            return ResponseEntity.ok().body(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/userinfo/{id}")
    public ResponseEntity<String> deleteUserInfo(@PathVariable String id){
        try {
            userInfoService.deleteUserInfo(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}