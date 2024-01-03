package com.sme.app.entity;

import jakarta.persistence.*;
import lombok.*;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "SME")
@Builder
public class Sme extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SME_SEQUENCE")
    @SequenceGenerator(name = "SME_SEQUENCE", sequenceName = "SME_SEQUENCE", allocationSize = 1)
    private Long id;
    private String name;
    private String code;
    private String description;
    private Boolean active;

}


