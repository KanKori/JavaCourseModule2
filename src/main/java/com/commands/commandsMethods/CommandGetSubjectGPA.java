package com.commands.commandsMethods;

import com.commands.ICommand;
import com.service.grade.GradeService;
import com.service.subject.SubjectService;

public class CommandGetSubjectGPA implements ICommand {
    @Override
        public void execute() {
        System.out.println("Best GPA subject: ");
        System.out.println(SubjectService.getInstance().getBestAVG() + "\n");
        System.out.println("Worst GPA subject: ");
        System.out.println(SubjectService.getInstance().getWorstAVG() + "\n");
        }
}
