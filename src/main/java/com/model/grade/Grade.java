package com.model.grade;

import com.model.student.Student;
import com.model.subject.Subject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Grade {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @ManyToMany(targetEntity = Student.class, cascade = CascadeType.ALL)
    @JoinTable(
            name = "student_grade",
            joinColumns = { @JoinColumn(name = "grade_id", referencedColumnName="id") },
            inverseJoinColumns = { @JoinColumn(name = "student_id", referencedColumnName="id")}
    )
    private List<Student> students;
    @OneToOne
    private Subject subject;
    private int value;

    public Grade(List<Student> students, Subject subject, int value) {
        this.students = students;
        this.subject = subject;
        this.value = value;
    }
}
