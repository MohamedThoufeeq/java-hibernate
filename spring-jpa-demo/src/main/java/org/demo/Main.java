package org.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);

        /*Controller controller = new Controller();
        controller.createUserInfoAndSave("john", "john@gmail.com");
        controller.createUserInfoAndSave("bob", "bob@gmail.com");

        controller.getAllUsers();*/
    }
}