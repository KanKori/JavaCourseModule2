package com.repository.grade;

import com.model.student.Student;

public interface IGardeRepository {
    double getStudentGPA(Student student);
}
