package com;

import com.commands.Commands;
import com.commands.ICommand;
import com.config.FlywayConfig;
import com.config.HibernateSessionFactoryUtil;
import org.flywaydb.core.Flyway;

import static com.commands.ICommand.SCANNER;

public class Main {
    public static void main(String[] args) {
        HibernateSessionFactoryUtil.getSessionFactory();
        Flyway flyway = FlywayConfig.getInstance();
        flyway.migrate();
        HibernateSessionFactoryUtil.getSessionFactory();
        final Commands[] values = Commands.values();
        boolean exit;

        do {
            exit = userAction(values);
        } while (!exit);
    }

    private static boolean userAction(final Commands[] values) {
        int userCommand = -1;
        do {
            for (int i = 0; i < values.length; i++) {
                System.out.printf("%d) %s%n", i, values[i].getName());
            }
            int input = SCANNER.nextInt();
            if (input >= 0 && input < values.length) {
                userCommand = input;
            }
        } while (userCommand == -1);
        final ICommand command = values[userCommand].getICommand();
        if (command == null) {
            return true;
        } else {
            command.execute();
            return false;
        }
    }
}
