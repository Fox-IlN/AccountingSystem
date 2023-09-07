package com.websys.accountingSystem.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Поле не может быть пустым")
    @Column(unique=true)
    private String name;

    @OneToMany(mappedBy = "district")
    private List<CollectionRecord> collectionRecords = new ArrayList<>();
}
