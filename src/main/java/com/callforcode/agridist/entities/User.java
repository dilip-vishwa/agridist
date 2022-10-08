package com.callforcode.agridist.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
//    user
//
//    username - varchar - unique name of user
//    password - varchar - password hashed
//    user_type - vendor, user
//    emailid - varchar - verified email id
//    mobileno - int - verified mobile no.
//    active - bool - user active or not
//    mobileno_verified - bool
//    emailid_verified - bool

    private String user_id;
    private String name;
    private String username;
    private String password_hash;
    private String user_type;
    private String emailid;
    private int mobileno;
    private boolean active;
    private boolean mobileno_verified;
    private boolean emailid_verified;
}