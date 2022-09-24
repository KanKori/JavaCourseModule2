package com.repository.student;

import com.model.student.Student;

import java.util.List;

public interface IStudentRepository {
    List<Student> findStudentWithGPAHigherThan(double inputGPA);
}
