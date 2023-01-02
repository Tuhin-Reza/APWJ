package com.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name ="tax_payer_cat")
public class TaxPayerCategory {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "*")
    @Column(name ="name")
    private String name;

}
