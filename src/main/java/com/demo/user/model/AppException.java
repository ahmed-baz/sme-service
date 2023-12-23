package com.demo.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "APP_EXCEPTION")
public class AppException implements Serializable {

    @Id
    private Long id;
    private String code;
    private String message;
    private String localizedMessage;

}


