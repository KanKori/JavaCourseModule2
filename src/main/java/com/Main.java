package com;

import com.model.student.Student;
import com.model.subject.Subject;
import com.repository.grade.GradeRepository;
import com.repository.student.StudentRepository;
import com.repository.subject.SubjectRepository;
import com.service.grade.GradeService;
import com.service.student.StudentService;
import com.service.subject.SubjectService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        StudentRepository studentRepository = new StudentRepository();
        StudentService studentService = new StudentService(studentRepository);
        Student student = studentService.create();
        studentRepository.save(student);
        Student student2 = studentService.create();
        studentRepository.save(student2);
        Student student3 = studentService.create();
        studentRepository.save(student3);
        Student student4 = studentService.create();
        studentRepository.save(student4);


        List<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student2);
        students.add(student3);
        students.add(student4);

        SubjectRepository subjectRepository = new SubjectRepository();
        SubjectService subjectService = new SubjectService(subjectRepository);
        Subject subject = subjectService.create();
        subjectRepository.save(subject);
        Subject subject2 = subjectService.create();
        subjectRepository.save(subject2);

        GradeRepository gradeRepository = new GradeRepository();
        GradeService gradeService = new GradeService(gradeRepository);
        gradeService.create(students, subject, 10);
        gradeService.create(students, subject2, 1);

    }
}
