package com.callforcode.agridist.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Equipment {
//    equipment
//
//    name - varchar - name of equipment like heavy pull tractor, sower, etc.
//    category - fk(equipment)
//    vendor_id - fk(vendor)

    private String name;
    private int category_id;
    private int vendor_id;

}
