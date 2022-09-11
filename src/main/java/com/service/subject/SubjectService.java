package com.service.subject;

import com.model.subject.Subject;
import com.repository.subject.SubjectRepository;
import com.service.AbstractCrudService;

import java.util.List;
import java.util.Optional;

public class SubjectService extends AbstractCrudService<Subject> {

    public SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository repository) {
        super(repository);
        this.subjectRepository = repository;
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

    public Subject getBestPerformance() {
        return subjectRepository.getBetterPerformance();
    }

    public Subject getWorstPerformance() {
        return subjectRepository.getWorstPerformance();
    }
}