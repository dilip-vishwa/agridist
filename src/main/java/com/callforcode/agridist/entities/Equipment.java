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

    private String equipment_id;
    private String name;
    private String category_id;
    private String vendor_id;

}
