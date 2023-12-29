package com.demo.user.model;

import com.demo.user.vo.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "APP_USER")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "APP_USER_SEQUENCE")
    @SequenceGenerator(name = "APP_USER_SEQUENCE", sequenceName = "APP_USER_SEQUENCE", allocationSize = 1)
    private Long id;
    private String name;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private boolean active;

}


