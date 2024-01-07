package com.sme.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Entity
@Table(name = "ADDRESS")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ADDRESS_SEQUENCE")
    @SequenceGenerator(name = "ADDRESS_SEQUENCE", sequenceName = "ADDRESS_SEQUENCE", allocationSize = 1)
    @Column(name = "ID", unique = true, nullable = false)
    private int id;
    @Column(name = "APARTMENT_NO")
    private String apartmentNo;
    @Column(name = "BUILDING_NO")
    private String buildingNo;
    @Column(name = "STREET_NAME")
    private String streetName;
    @Column(name = "AREA")
    private String area;
    @Column(name = "CITY")
    private String city;

}
