package com.commands.commandsMethods;

import com.commands.ICommand;
import com.model.group.Group;
import com.service.group.GroupService;

import java.util.Map;

public class CommandGetCountStudentInEveryGroup implements ICommand {
    @Override
    public void execute() {
        System.out.println("Group students count: ");
        Map<Group, Integer> groupStudentsCount = GroupService.getInstance().getCountStudentInEveryGroup();
        for (Map.Entry<Group, Integer> entry : groupStudentsCount.entrySet()) {
            System.out.println("Group: " + entry.getKey() + " students count: " + entry.getValue());
        }
    }
}