package Bartek.libsys.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Bartek.libsys.model.User;

public class UserData {

      // Get user by login
      private static final Map<String, User> users = new HashMap<>();
      private static List<User> dummyDataUsers = new ArrayList<>();

      static {
            // Initialize sample users
            initializeUsers();
      }

      // Initialize sample users
      public static void initializeUsers() {
            dummyDataUsers.add(new User(1, "admin", "d21674e85d9271a492986ebb09d9e6c0df2d0120ce418e6549b5f41ebb00a0c0",
                        "ADMIN"));
            dummyDataUsers.add(new User(2, "admin2", "d21674e85d9271a492986ebb09d9e6c0df2d0120ce418e6549b5f41ebb00a0c0",
                        "ADMIN"));
            dummyDataUsers.add(new User(3, "Janusz", "d21674e85d9271a492986ebb09d9e6c0df2d0120ce418e6549b5f41ebb00a0c0",
                        "USER"));
            dummyDataUsers.add(new User(4, "Adam", "d21674e85d9271a492986ebb09d9e6c0df2d0120ce418e6549b5f41ebb00a0c0",
                        "USER"));

            for (User user : dummyDataUsers) {
                  users.put(user.getLogin(), user);
            }
      }

      public User getByLogin(String login) {
            return users.get(login);
      }

      public static void addUser(User user) {
            dummyDataUsers.add(user);
            users.put(user.getLogin(), user);
      }

      public static List<User> getAllUsers() {
            return dummyDataUsers;
      }

      public static void printAllUsers() {
            System.out.println("ID  | Username | Role");
            System.out.println("----------------------");
            for (User user : dummyDataUsers) {
                  System.out.printf("%-4d| %-9s| %s%n", user.getid(), user.getLogin(), user.getRole());
            }
      }

      // Add a new user
      public static void addNewUser(String username, String userPassword, String role) {
            int id = dummyDataUsers.size() + 1; // Generate ID sequentially
            User newUser = new User(id, username, userPassword, role);
            addUser(newUser);
            System.out.println("User added successfully." + "\n" + newUser.toString());
      }

      public static void deleteUserById(int userId) {
            for (User user : dummyDataUsers) {
                  if (user.getid() == userId) {
                        dummyDataUsers.remove(user);
                        users.remove(user.getLogin());
                        System.out.println("User deleted successfully.");
                        return;
                  }
            }
            System.out.println("User with ID " + userId + " not found.");
      }

}
