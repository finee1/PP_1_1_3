package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        User user1 = new User("John", "Spring", (byte) 5);
        User user2 = new User("Anita", "Warm", (byte) 50);
        User user3 = new User("Ken", "Long", (byte) 66);
        User user4 = new User("Steave", "Min", (byte) 102);

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();
        userService.dropUsersTable();




    }
}
