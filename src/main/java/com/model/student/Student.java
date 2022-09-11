package com.model.student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private LocalDate admissionDate;

    public Student(String firstName, String lastName, int age, LocalDate admissionDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.admissionDate = admissionDate;
    }
}
