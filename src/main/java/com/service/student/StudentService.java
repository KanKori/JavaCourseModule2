package com.service.student;

import com.model.student.Student;
import com.repository.grade.GradeRepository;
import com.repository.student.StudentRepository;
import com.service.AbstractCrudService;

import java.util.List;
import java.util.Optional;

public class StudentService extends AbstractCrudService<Student> {

    StudentRepository studentRepository;

    public StudentService(StudentRepository repository) {
        super(repository);
        this.studentRepository = repository;
    }

    public Student create() {
        return studentRepository.create();
    }
    public void save(Student student) {
        studentRepository.save(student);
    }

    public void saveAll (List<Student> students) {
        studentRepository.saveAll(students);
    }

    public List<Student> getAll() {
        return studentRepository.getAll();
    }

    public Optional<Student> findById(String id) {
        return studentRepository.findById(id);
    }

    public void update(Student student) {
        studentRepository.update(student);
    }

    public void delete(String id) {
        studentRepository.delete(id);
    }

    public List<Student> findStudentWithGPAHigherThan(double inputGPA, GradeRepository gradeRepository) {
        return studentRepository.findStudentWithGPAHigherThan(inputGPA, gradeRepository);
    }
}
