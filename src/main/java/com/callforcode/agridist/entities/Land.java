package com.callforcode.agridist.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Land {
//    land
//
//    user_id - fk(user)
//    land_size - varchar - in acre/bigha
//    is_it_combined_with_others - bool - if land is combined and equipment will be used for all.
//    combined_land_size - varchar - in acre/bigha

    private int user_id;
    private String land_size;
    private boolean is_it_combined_with_others;
    private String combined_land_size;
}
