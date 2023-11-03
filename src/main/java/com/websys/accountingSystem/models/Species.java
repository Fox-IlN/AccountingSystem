package com.websys.accountingSystem.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.websys.accountingSystem.util.EntityIdResolver;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        scope = Species.class,
        resolver = EntityIdResolver.class,
        property = "id"
)
public class Species implements ComboListItem  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Поле не может быть пустым")
    @Column(unique=true)
    private String name;

    @OneToMany(mappedBy = "species")
    @JsonIdentityReference(alwaysAsId = true)
    private List<CollectionRecord> collectionRecords = new ArrayList<>();

}

