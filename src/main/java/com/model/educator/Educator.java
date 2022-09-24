package com.model.educator;

import com.model.subject.Subject;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class Educator {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private int age;
    @OneToOne(cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER)
    private Subject subject;
}
