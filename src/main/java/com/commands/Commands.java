package com.commands;

import com.commands.commandsMethods.CommandExit;
import com.commands.commandsMethods.CommandFindByFirstAndLastName;
import com.commands.commandsMethods.CommandFindGroupByName;
import com.commands.commandsMethods.CommandFindStudentWithGPAHigherThan;
import com.commands.commandsMethods.CommandGetCountGroupsGPA;
import com.commands.commandsMethods.CommandGetCountStudentInEveryGroup;
import com.commands.commandsMethods.CommandGetSubjectGPA;
import com.config.HibernateSessionFactoryUtil;
import lombok.Getter;
import org.hibernate.SessionFactory;

@Getter
public enum Commands {
    FIND_EDUCATOR_BY_FIRST_AND_LAST_NAME
            ("Find educator by first and last name", new CommandFindByFirstAndLastName()),
    FIND_GROUP_BY_NAME
            ("Find group by name", new CommandFindGroupByName()),
    FIND_STUDENT_WITH_GPA_HIGHER_THAN
            ("Find student with GPA higher than input value", new CommandFindStudentWithGPAHigherThan()),
    GET_COUNT_GROUPS_GPA
            ("Get count groups GPA", new CommandGetCountGroupsGPA()),
    GET_COUNT_STUDENT_IN_EVERY_GROUP
            ("Get count student in every group", new CommandGetCountStudentInEveryGroup()),
    GET_SUBJECT_GPA
            ("Get subject GPA", new CommandGetSubjectGPA()),
    EXIT
            ("Exit", new CommandExit());

    private final String name;
    private final ICommand ICommand;
    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    Commands(String name, ICommand ICommand) {
        this.name = name;
        this.ICommand = ICommand;
    }
}
