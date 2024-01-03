package com.sme.app.vo;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressVo {

    private int id;
    private String apartmentNo;
    private String buildingNo;
    private String streetName;
    private String area;
    private String city;

}
