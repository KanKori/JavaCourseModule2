package com.service.group;

import com.model.group.Group;
import com.repository.grade.GradeRepository;
import com.repository.group.GroupRepository;
import com.service.AbstractCrudService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GroupService extends AbstractCrudService<Group> {

    GroupRepository groupRepository;

    public GroupService(GroupRepository repository) {
        super(repository);
        this.groupRepository = repository;
    }

    public void save(Group group) {
        groupRepository.save(group);
    }

    public void saveAll (List<Group> groups) {
        groupRepository.saveAll(groups);
    }

    public List<Group> getAll() {
        return groupRepository.getAll();
    }

    public Optional<Group> findById(String id) {
        return groupRepository.findById(id);
    }

    public void update(Group group) {
        groupRepository.update(group);
    }

    public void delete(String id) {
        groupRepository.delete(id);
    }

    public List<Group> findGroupByName(String name) {
        return groupRepository.findGroupByName(name);
    }

    public Map<Group, Integer> getCountStudentInEveryGroup() {
        return groupRepository.getCountStudentInEveryGroup();
    }

    public Map<Group, Double> getCountGroupsGPA(GradeRepository gradeRepository) {
        return groupRepository.getCountGroupsGPA(gradeRepository);
    }
}
