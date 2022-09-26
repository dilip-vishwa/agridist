package com.callforcode.agridist.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EquipmentIssue {
//    log_equipment_issue
//
//    user_id - fk(user) - user who logged issue
//    equipment_id - fk(equipment) - for which equipment issue is logged
//    issue_subject - varchar
//    issue_description - varchar
//    issue_status - int - 1. pending, 2. ongoing, 3. resolved
//    vendor_who_fixed_issue - fk(vendor)
//    is_verified_by_vendor - bool
//    user_who_verified_issue_fixed - fk(user)
//    is_verified_by_user - bool

    private String equipmentIssue_id;
    private String user_id;
    private String equipment_id;
    private String issue_subject;
    private String issue_description;
    private int issue_status;
    private int vendor_who_fixed_issue;
    private boolean is_verified_by_vendor;
    private int user_who_verified_issue_fixed;
    private boolean is_verified_by_user;

}
