package com.repository.group;

import com.model.group.Group;
import com.repository.grade.GradeRepository;

import java.util.List;
import java.util.Map;

public interface IGroupRepository {
    List<Group> findGroupByName(String name);

    Map<Group, Integer> getCountStudentInEveryGroup();

    Map<Group, Double> getCountGroupsGPA(GradeRepository gradeRepository);
}
