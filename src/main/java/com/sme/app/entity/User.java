package com.sme.app.entity;

import com.sme.app.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "APP_USER")
public class User extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "APP_USER_SEQUENCE")
    @SequenceGenerator(name = "APP_USER_SEQUENCE", sequenceName = "APP_USER_SEQUENCE", allocationSize = 1)
    private Long id;
    private String name;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private boolean active;
    @ManyToOne
    @JoinColumn(name = "SME_Id")
    private Sme sme;

}


