package com.commands.commandsMethods;

import com.commands.ICommand;
import com.model.student.Student;
import com.service.student.StudentService;

import java.util.List;

public class CommandFindStudentWithGPAHigherThan implements ICommand {

    @Override
    public void execute() {
        System.out.println("Enter average grade: ");
        double avg = Double.parseDouble(SCANNER.nextLine());
        List<Student> students = StudentService.getInstance().findStudentWithGPAHigherThan(avg);
        System.out.println("Students with average grade higher than " + avg + ":");
        if (students.size() > 0) {
            students.forEach(System.out::println);
        } else {
            System.out.println("Not found");
        }
    }
}
