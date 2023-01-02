package com.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name ="tax_zone")
public class TaxZone {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "*")
    @Column(name ="zone")
    private String zone;

    @NotNull(message = "*")
    @Column(name ="percentage")
    private Double percentage;
}
