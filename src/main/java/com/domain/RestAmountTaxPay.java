package com.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name ="rest_amount_tax_pay")
public class RestAmountTaxPay {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "*")
    @Column(name ="amount")
    private Double amount;

    @NotNull(message = "*")
    @Column(name ="percentage")
    private Double percentage;

}
