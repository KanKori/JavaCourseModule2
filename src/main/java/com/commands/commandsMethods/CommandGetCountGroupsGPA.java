package com.commands.commandsMethods;

import com.commands.ICommand;
import com.model.group.Group;
import com.service.group.GroupService;

import java.util.Map;

public class CommandGetCountGroupsGPA implements ICommand {
    @Override
        public void execute() {
        System.out.println("GPA in group");
        Map<Group, Double> GPAInGroup = GroupService.getInstance().getCountGroupsGPA();
        GPAInGroup.forEach((k, v) -> System.out.println("Group: " + k.getName() + ", GPA = " + v));
        }
}
