package com.repository.educator;

import com.model.educator.Educator;

import java.util.List;

public interface IEducatorRepository {
    List<Educator> findByFirstAndLastName(String firstName, String lastName);
}
