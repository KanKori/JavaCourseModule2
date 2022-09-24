package com.service.subject;

import com.model.subject.Subject;
import com.repository.subject.SubjectRepository;
import com.service.AbstractCrudService;

import java.util.List;
import java.util.Optional;

public class SubjectService extends AbstractCrudService<Subject> {

    private static SubjectService instance;
    private final SubjectRepository subjectRepository = SubjectRepository.getInstance();

    public SubjectService(){
        super(SubjectRepository.getInstance());
    }

    public static SubjectService getInstance(){
        if(instance==null){
            instance = new SubjectService();
        }
        return instance;
    }

    public Subject create() {
        return subjectRepository.create();
    }

    public void save(Subject subject) {
        subjectRepository.save(subject);
    }

    public void saveAll(List<Subject> subjects) {
        subjectRepository.saveAll(subjects);
    }

    public List<Subject> getAll() {
        return subjectRepository.getAll();
    }

    public Optional<Subject> findById(String id) {
        return subjectRepository.findById(id);
    }

    public void update(Subject subject) {
        subjectRepository.update(subject);
    }

    public void delete(String id) {
        subjectRepository.delete(id);
    }

    public Subject getBestAVG() {
        return subjectRepository.getBestAvg();
    }

    public Subject getWorstAVG() {
        return subjectRepository.getWorstAVG();
    }
}