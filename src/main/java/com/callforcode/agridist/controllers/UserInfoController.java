package com.callforcode.agridist.controllers;

import com.callforcode.agridist.entities.UserInfo;
import com.callforcode.agridist.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;

    @GetMapping("/userinfo")
    public List<UserInfo> getAllUserInfo( ) throws InterruptedException, ExecutionException{
        return userInfoService.getUserInfos();
    }

    @GetMapping("/userinfo/{id}")
    public UserInfo getUserInfo(@PathVariable String id ) throws InterruptedException, ExecutionException{
        return userInfoService.getUserInfoDetails(id);
    }

    @PostMapping("/userinfo")
    public String createUserInfo(@RequestBody UserInfo userInfo ) throws InterruptedException, ExecutionException {
        return userInfoService.saveUserInfoDetails(userInfo);
    }

    @PutMapping("/userinfo/{id}")
    public String updateUserInfo(@RequestBody UserInfo userInfo , @PathVariable String id ) throws InterruptedException, ExecutionException {
        return userInfoService.updateUserInfoDetails(userInfo, id);
    }

    @DeleteMapping("/userinfo/{id}")
    public String deleteUserInfo(@PathVariable String id){
        return userInfoService.deleteUserInfo(id);
    }
}