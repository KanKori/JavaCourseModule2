package com.repository.student;

import com.model.student.Student;
import com.repository.grade.GradeRepository;

import java.util.List;

public interface IStudentRepository {
    List<Student> findStudentWithGPAHigherThan(double value, GradeRepository gradeRepository);
}
