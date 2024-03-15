package Bartek.libsys;

import java.util.Scanner;

import Bartek.libsys.auth.Auth;
import Bartek.libsys.db.BOOKDB;
import Bartek.libsys.db.UserData;

/**
 * JavaFX App
 */
public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isAdmin = false;
        boolean isLoggedIn = false;

        BOOKDB bookdb = new BOOKDB();
        Auth auth = new Auth();
        UserData userData = new UserData();

        while (true) {
            System.out.println("Welcome to the Library System!");
            System.out.println("1. Login ");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1: {
                    System.out.println("Welcome to the Library System!" + "\n");
                    System.out.print("Enter your username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine();
                    if (auth.authenticate(username, password)) {
                        System.out.println("Logged in successfully. Welcome, " + auth.loggedUser.getLogin());

                        if (auth.loggedUser.getRole().equalsIgnoreCase("ADMIN")) {
                            isAdmin = true;
                        } else {
                            isAdmin = false;
                        }

                        isLoggedIn = true;
                    } else {
                        System.out.println("Failed to log in.");

                    }
                }
                    break;
                case 2: {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
                    continue;
            }

            while (isLoggedIn) {
                System.out.println("Welcome to the Library!");
                System.out.println("1. View Available Books");
                System.out.println("2. View All Books");
                if (isAdmin) {
                    System.out.println("3. View Books Rented by Me");
                    System.out.println("4. Add Book");
                    System.out.println("5. Lease Book");
                    System.out.println("6. Remove Book");
                    System.out.println("7. Add User");
                    System.out.println("8. Delete User");
                    System.out.println("9. ReturnBook");
                    System.out.println("10. Look for");
                    System.out.println("11. Userlist");
                    System.out.println("12. Books Rented by users");
                }
                System.out.println("999. Logout");
                System.out.print("Choose an option: ");

                option = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (option) {
                    case 1: {
                        System.out.println("Viewing Available Books...");
                        bookdb.printAvailableBooks();
                    }
                        break;
                    case 2: {
                        System.out.println("Viewing All Books...");
                        bookdb.printAllBooks();
                    }
                        break;
                    case 3: {
                        System.out.println("Viewing Books Rented by Me...");
                        bookdb.printBooksRentedByUser(auth.loggedUser.getLogin());
                    }
                        break;
                    case 4:
                        if (isAdmin) {
                            System.out.println("Adding Book...");
                            System.out.print("Enter the title of the book: ");
                            String title = scanner.nextLine();
                            System.out.print("Enter the author of the book: ");
                            String author = scanner.nextLine();
                            bookdb.addNewBook(title, author);
                            // Implement logic to add a book
                        }
                        break;
                    case 5:
                        if (isAdmin) {
                            System.out.println("Leasing Book...");
                            System.out.print("Enter the ID of the book to lease: ");
                            int id = scanner.nextInt();
                            System.out.print("Enter the username to lease to: ");
                            String username = scanner.nextLine();
                            bookdb.leaseBook(username, id);
                        }
                        break;
                    case 6:
                        if (isAdmin) {
                            System.out.println("Removing Book...");
                            System.out.print("Enter the ID of the book to remove: ");
                            int id = scanner.nextInt();
                            bookdb.removeBookById(id);
                        }
                        break;
                    case 7:
                        if (isAdmin) {
                            System.out.println("Adding User...");
                            System.out.print("Enter the username: ");
                            String username = scanner.nextLine();
                            System.out.print("Enter the password: ");
                            String password = scanner.nextLine();
                            String hashedPassword = Auth.ChangetoHashed(password);
                            System.out.print("Enter the role: ");
                            String role = scanner.nextLine();
                            userData.addNewUser(username, hashedPassword, role);

                        }
                        break;
                    case 8:
                        if (isAdmin) {
                            System.out.println("Deleting User...");
                            System.out.print("Enter the ID of the user to delete: ");
                            int id = scanner.nextInt();
                            userData.deleteUserById(id);
                        }
                        break;
                    case 9:
                        if (isAdmin) {
                            System.out.println("return book");
                            System.out.println("Enter the id: ");
                            int ID = scanner.nextInt();
                            bookdb.returnBook(ID);
                        }
                        break;
                    case 10:
                        if (isAdmin) {
                            System.out.println("look for user(1) or book(2) ");
                            int response = scanner.nextInt();

                            if (response == 1) {
                                System.out.println("Enter the username: ");
                                String username = scanner.nextLine();
                            }
                            if (response == 2) {
                                String input = "";
                                while (true) {

                                    if (input.equalsIgnoreCase("!stop")) {
                                        System.out.println("Exiting search.");
                                        break;
                                    }
                                    bookdb.searchAndUpdate(input);
                                    System.out.print("Enter search keyword or !stop to exit: ");
                                    input = scanner.nextLine().trim();

                                }

                                scanner.close();
                            }

                        }
                        break;
                    case 11:
                        if (isAdmin) {
                            System.out.println("All Users...");
                            userData.printAllUsers();
                        }
                        break;
                    case 12:
                        if (isAdmin) {
                            System.out.println("Books rented by users...");
                            System.out.println("Enter the username: ");
                            String username = scanner.nextLine();
                            bookdb.printBooksRentedByUser(username);

                        }
                        break;
                    case 999:
                        isLoggedIn = false;
                        System.out.println("Logged out successfully.");
                        break;
                    default:
                        System.out.println("Invalid option, please try again.");
                }
            }
        }
    }

}
