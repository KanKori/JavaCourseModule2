package com.commands.commandsMethods;

import com.commands.ICommand;
import com.model.educator.Educator;
import com.service.educator.EducatorService;

import java.util.List;

public class CommandFindByFirstAndLastName implements ICommand {

    @Override
    public void execute() {
        System.out.println("Enter first name of educator you want to find:");
        String firstName = SCANNER.nextLine();
        System.out.println("Enter last name of educator you want to find:");
        String lastName = SCANNER.nextLine();
        List<Educator> educators = EducatorService.getInstance().findByFirstAndLastName(firstName, lastName);
        if (educators.size() > 0) {
            System.out.println("Found educators: ");
            educators.forEach(System.out::println);
        } else {
            System.out.println("No one found");
        }
    }
}
