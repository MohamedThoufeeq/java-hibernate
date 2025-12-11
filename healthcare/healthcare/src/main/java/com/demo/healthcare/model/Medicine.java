package com.demo.healthcare.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "medicine")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "medicine_id")
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "medicines")
    private List<Prescription> prescriptions;

}
