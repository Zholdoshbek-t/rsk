package com.rsk.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @OneToMany(mappedBy = "employee")
    private List<Equipment> equipments = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    private List<Payment> payments = new ArrayList<>();
}
