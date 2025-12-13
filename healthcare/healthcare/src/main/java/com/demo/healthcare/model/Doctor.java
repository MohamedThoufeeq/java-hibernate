package com.demo.healthcare.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
public class Doctor extends Person {

    public Doctor(String designation) {this.designation=designation;}
    /*
    Doctor can have many patients
     */
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @ToString.Exclude @EqualsAndHashCode.Exclude // this is to tell lombok to exclude from the ToString, Equals, Hash method generations internally in order to stop the infinite loop
    @Getter @Setter
    private List<Patient> patients;

    @Getter @Setter
    private String designation;

    @CreationTimestamp
    @Getter(AccessLevel.PRIVATE) @Setter(AccessLevel.PRIVATE)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Getter(value = AccessLevel.PRIVATE) @Setter(value = AccessLevel.PRIVATE)
    private LocalDateTime updatedAt;

    //will execute only when INSERT query is executed
    @PrePersist
    private void updateRole(){
        this.setRole(Role.DOCTOR);
    }
}
