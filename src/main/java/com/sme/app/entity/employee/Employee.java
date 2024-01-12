package com.sme.app.entity.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sme.app.entity.Address;
import com.sme.app.entity.EntityBase;
import com.sme.app.entity.Sme;
import jakarta.persistence.*;
import org.hibernate.annotations.Index;
import lombok.*;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "EMPLOYEE")
@Builder
@NamedEntityGraph(name = "employee_entity_graph", attributeNodes = @NamedAttributeNode("address"))
public class Employee extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "EMPLOYEE_SEQUENCE")
    @SequenceGenerator(name = "EMPLOYEE_SEQUENCE", sequenceName = "EMPLOYEE_SEQUENCE", allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal salary;
    @ManyToOne
    @JoinColumn(name = "SME_Id")
    @Index(name = "IDX_SME_NAME")
    private Sme sme;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "EMPLOYEE_ID")
    private List<Address> address;
    @JsonFormat(pattern = "yyyy/MM/dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "JOIN_DATE")
    private Date joinDate;

}


