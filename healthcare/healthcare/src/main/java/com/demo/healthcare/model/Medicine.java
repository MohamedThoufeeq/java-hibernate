package com.demo.healthcare.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
public class Medicine {

    public Medicine(String name) {this.name = name;}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicine_id")
    private Long id;

    @Getter @Setter
    private String name;

    @ManyToMany(mappedBy = "medicines")
    @Getter @Setter
    private List<Prescription> prescriptions;


}
