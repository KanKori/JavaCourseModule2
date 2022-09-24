package com.repository.subject;

import com.model.subject.Subject;

public interface ISubjectRepository {
    Subject getBestAvg();

    Subject getWorstAVG();

}
