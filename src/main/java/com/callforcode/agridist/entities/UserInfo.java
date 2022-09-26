package com.callforcode.agridist.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserInfo {
//    userinfo
//
//    user_id - fk(user)
//    address - varchar - address of user
//    location - varchar - geohash
//    pincode - int

    private String userInfo_id;
    private String user_id;
    private String address;
    private String location;
    private int pincode;
}
