/*
package com.websys.accountingSystem.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Поле фамилии не может быть пустым")
    private String lastName;

    private String firstName;

    private String patronymicName;

    @ManyToMany(mappedBy = "collectorBy")
    private Set<CollectionRecord> collectors = new HashSet<>();

    @ManyToMany(mappedBy = "identifiedBy")
    private Set<CollectionRecord> identified = new HashSet<>();
}

*/
