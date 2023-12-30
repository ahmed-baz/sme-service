package com.sme.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "APP_EXCEPTION")
public class AppException extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "APP_EXCEPTION_SEQUENCE")
    @SequenceGenerator(name = "APP_EXCEPTION_SEQUENCE", sequenceName = "APP_EXCEPTION_SEQUENCE", allocationSize = 1)
    private Long id;
    private String code;
    private String message;
    private String localizedMessage;

}


