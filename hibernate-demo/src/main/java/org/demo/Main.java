package org.demo;

import org.demo.contoller.Controller;
public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.createUserInfoAndSave("john", "john@gmail.com");
        controller.createUserInfoAndSave("bob", "bob@gmail.com");

        controller.getAllUsers();
    }
}