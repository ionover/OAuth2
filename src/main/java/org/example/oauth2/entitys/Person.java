package org.example.oauth2.entitys;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.oauth2.entitys.enums.Gender;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 1023)
    private String name;

    @Column(nullable = false)
    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private String surname;

    @Embedded
    private Contact contact;

    @ManyToOne(optional = false)
    private City city;
}
