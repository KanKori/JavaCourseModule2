package com.service.grade;

import com.model.grade.Grade;
import com.model.student.Student;
import com.model.subject.Subject;
import com.repository.grade.GradeRepository;
import com.service.AbstractCrudService;

import java.util.List;
import java.util.Optional;

public class GradeService extends AbstractCrudService<Grade> {

    private static GradeService instance;
    private final GradeRepository gradeRepository = GradeRepository.getInstance();

    public GradeService() {
        super(GradeRepository.getInstance());
    }

    public static GradeService getInstance(){
        if(instance==null){
            instance = new GradeService();
        }
        return instance;
    }

    public Grade create(List<Student> students, Subject subject, int value) {
        return new Grade( students, subject, value);
    }

    public void save(Grade grade) {
        gradeRepository.save(grade);
    }

    public void saveAll (List<Grade> grades) {
        gradeRepository.saveAll(grades);
    }

    public List<Grade> getAll() {
        return gradeRepository.getAll();
    }

    public Optional<Grade> findById(String id) {
        return gradeRepository.findById(id);
    }

    public void update(Grade grade) {
        gradeRepository.update(grade);
    }

    public void delete(String id) {
         gradeRepository.delete(id);
    }

    public double getStudentGPA(Student student) {
        return gradeRepository.getStudentGPA(student);
    }
}
