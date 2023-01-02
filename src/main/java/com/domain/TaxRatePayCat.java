package com.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name ="tax_rate_pay_cat")
public class TaxRatePayCat {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "*")
    @Column(name ="category")
    private String category;

    @NotNull(message = "*")
    @Column(name ="amount")
    private Double amount;

    @NotNull(message ="*")
    @Column(name ="percentage")
    private Double percentage;

}
