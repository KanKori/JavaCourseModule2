package com.service.student;

import com.model.student.Student;
import com.repository.student.StudentRepository;
import com.repository.student.StudentRepository;
import com.service.AbstractCrudService;
import com.service.student.StudentService;

import java.util.List;
import java.util.Optional;

public class StudentService extends AbstractCrudService<Student> {

    private static StudentService instance;
    private final StudentRepository studentRepository = StudentRepository.getInstance();

    public StudentService(){
        super(StudentRepository.getInstance());
    }

    public static StudentService getInstance(){
        if(instance==null){
            instance = new StudentService();
        }
        return instance;
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

    public List<Student> findStudentWithGPAHigherThan(double inputGPA) {
        return studentRepository.findStudentWithGPAHigherThan(inputGPA);
    }
}
