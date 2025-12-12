package com.demo.healthcare.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
//@Inheritance(strategy = InheritanceType.JOINED)
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "person_type")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private int age;
    private String email;
    @Column(name = "mobile_number")
    private long mobileNumber;

    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private String ageGroup;//make this field hidden in the code

    @Enumerated(EnumType.STRING)
    private GENDER gender;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Embedded
    private Address address;

    @Version
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private int version;

    @PrePersist
    @PreUpdate
    private void updateAgeGroup(){
        this.ageGroup = calculateAgeGroup(this.age);
    }

    private String calculateAgeGroup(int age){
        if (age < 13) return "CHILD";
        else if (age < 18) return "TEEN";
        else if (age < 60) return "ADULT";
        else return "SENIOR";
    }
}
