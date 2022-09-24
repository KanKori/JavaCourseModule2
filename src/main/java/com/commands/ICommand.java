package com.commands;

import java.util.Scanner;

public interface ICommand {
    void execute();

    Scanner SCANNER = new Scanner(System.in);
}