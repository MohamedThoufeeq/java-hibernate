package com.demo.healthcare.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data@Builder@AllArgsConstructor@NoArgsConstructor
public class Address {
    private  String street;
    private String city;
    private String state;
    private String country;
    private Integer zipcode;
}
