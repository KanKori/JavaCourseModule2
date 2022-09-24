package com.commands.commandsMethods;

import com.commands.ICommand;
import com.model.group.Group;
import com.service.group.GroupService;

import java.util.List;

public class CommandFindGroupByName implements ICommand {

    @Override
        public void execute() {
            System.out.println("Enter group name");
            String name = SCANNER.nextLine();
            List<Group> groups = GroupService.getInstance().findGroupByName(name);
            if (groups.size() > 0) {
                System.out.println("Fou nd group: ");
                groups.forEach(System.out::println);
            } else {
                System.out.println("Groups not found");
            }
        }
}
