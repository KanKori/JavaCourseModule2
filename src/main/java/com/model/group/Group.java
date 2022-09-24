package com.model.group;

import com.model.student.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Group {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column
    String name;
    @OneToMany(cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    @JoinTable(
            name = "group_student",
            joinColumns = { @JoinColumn(name = "group_id", referencedColumnName="id") },
            inverseJoinColumns = { @JoinColumn(name = "student_id", referencedColumnName="id")}
    )
    List<Student> students;
}
