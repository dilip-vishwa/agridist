package com.callforcode.agridist.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Hire {
//    hire
//
//    user_id - fk(user)
//    start_datetime - datetime
//    end_datetime - datetime
//    hire_at - varchar - geohash - if user do not want to hire for base location i.e. user's address

    private String hire_id;
    private String user_id;
    private String start_datetime;
    private String end_datetime;
    private String hire_at;
}
