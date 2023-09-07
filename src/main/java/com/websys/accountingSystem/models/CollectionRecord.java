package com.websys.accountingSystem.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.websys.accountingSystem.util.EntityIdResolver;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Entity
@Setter
@Getter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        scope = CollectionRecord.class,
        resolver = EntityIdResolver.class,
        property = "id"
)
public class CollectionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Уникальный ключ не может быть пустым")
    @Column(unique=true)
    private String uniqueKey;

    @ManyToOne
    private Region region;

    @ManyToOne
    private District district;

    @ManyToOne/*
    @JsonIdentityReference(alwaysAsId = true)*/
    private Species species;


    private String associatedSpecies;
/*    @ManyToOne
    private AssociatedSpecies associatedSpecies;*/

    @ManyToOne
    private HerbariumLocation herbariumLocation;

    /*@ManyToMany
    @JoinTable(name = "collectionRecordCollectorBy",
            joinColumns = @JoinColumn(name = "collectionRecordId"),
            inverseJoinColumns = @JoinColumn(name = "employeeId")
    )
    private Set<Employee> collectorBy = new HashSet<>();*/

    /*@ManyToMany
    @JoinTable(name = "collectionRecordIdentifiedBy",
            joinColumns = @JoinColumn(name = "collectionRecordId"),
            inverseJoinColumns = @JoinColumn(name = "employeeId")
    )
    private Set<Employee> identifiedBy = new HashSet<>();*/

    private String collectorBy;

    private String identifiedBy;

    private String localityExact;

    private String habitat;

    private String altitude;

    private String latitude;

    private String longitude;

    @Temporal(TemporalType.DATE)
    private Date collectionDate;/*
    private String collectionDate;*/

    private String presenceSporophyte;

/*    @Temporal(TemporalType.DATE)
    private Date collectionDate;

    @AssertFalse
    private Boolean presenceSporophyte;*/

}
