package com.service.educator;

import com.model.educator.Educator;
import com.repository.educator.EducatorRepository;
import com.service.AbstractCrudService;

import java.util.List;
import java.util.Optional;

public class EducatorService extends AbstractCrudService<Educator> {

    private static EducatorService instance;
    private final EducatorRepository educatorRepository = EducatorRepository.getInstance();

    public EducatorService(){
        super(EducatorRepository.getInstance());
    }

    public static EducatorService getInstance(){
        if(instance==null){
            instance = new EducatorService();
        }
        return instance;
    }

    public void save(Educator educator) {
        educatorRepository.save(educator);
    }

    public void saveAll (List<Educator> educators) {
        educatorRepository.saveAll(educators);
    }

    public List<Educator> getAll() {
        return educatorRepository.getAll();
    }

    public Optional<Educator> findById(String id) {
        return educatorRepository.findById(id);
    }

    public void update(Educator educator) {
        educatorRepository.update(educator);
    }

    public void delete(String id) {
        educatorRepository.delete(id);
    }

    public List<Educator> findByFirstAndLastName(String firstName, String lastName) {
        return educatorRepository.findByFirstAndLastName(firstName, lastName);
    }
}
