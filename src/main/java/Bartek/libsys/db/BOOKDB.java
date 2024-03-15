package Bartek.libsys.db;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Bartek.libsys.model.Book;

public class BOOKDB {
      private static List<Book> dummyData = new ArrayList<>();
      private static int nextId = 21; // Starting ID
      // Initialize sample books
      static {
            initializeBooks();
      }

      // Initialize sample books
      public static void initializeBooks() {
            // Adding sample books
            dummyData.add(new Book(1, "To Kill a Mockingbird", "Harper Lee", false, null, null, ""));
            dummyData.add(new Book(2, "1984", "George Orwell", false, null, null, ""));
            dummyData.add(new Book(3, "The Great Gatsby", "F. Scott Fitzgerald", false, null, null, ""));
            dummyData.add(new Book(4, "Pride and Prejudice", "Jane Austen", false, null, null, ""));
            dummyData.add(new Book(5, "The Catcher in the Rye", "J.D. Salinger", false, null, null, ""));
            dummyData.add(new Book(6, "Harry Potter and the Sorcerer's Stone", "J.K. Rowling", false, null, null, ""));
            dummyData.add(new Book(7, "The Lord of the Rings", "J.R.R. Tolkien", false, null, null, ""));
            dummyData.add(new Book(8, "Brave New World", "Aldous Huxley", true, null, null, "admin"));
            dummyData.add(new Book(9, "The Hunger Games", "Suzanne Collins", false, null, null, ""));
            dummyData.add(new Book(10, "The Hitchhiker's Guide to the Galaxy", "Douglas Adams", false, null, null, ""));
            dummyData.add(new Book(11, "To Kill a Mockingbird", "Harper Lee", false, null, null, ""));
            dummyData.add(new Book(12, "1984", "George Orwell", true, null, null, "admin"));
            dummyData.add(new Book(13, "The Great Gatsby", "F. Scott Fitzgerald", false, null, null, ""));
            dummyData.add(new Book(14, "Pride and Prejudice", "Jane Austen", false, null, null, ""));
            dummyData.add(new Book(15, "The Catcher in the Rye", "J.D. Salinger", false, null, null, ""));
            dummyData.add(new Book(16, "The Hobbit", "J.R.R. Tolkien", false, null, null, ""));
            dummyData.add(new Book(17, "Fahrenheit 451", "Ray Bradbury", false, null, null, ""));
            dummyData.add(new Book(18, "The Picture of Dorian Gray", "Oscar Wilde", false, null, null, ""));
            dummyData.add(new Book(19, "Frankenstein", "Mary Shelley", false, null, null, ""));
            dummyData.add(new Book(20, "Animal Farm 2", "George Orwell", false, null, null, ""));
      }

      public static List<Book> getAllBooks() {
            return dummyData;
      }

      // Add a new book
      public static void addBook(Book book) {
            dummyData.add(book);
      }

      // Remove a book
      public static void removeBook(Book book) {
            dummyData.remove(book);
      }

      // Remove a book by its ID
      public static void removeBookById(int id) {
            Book bookToRemove = null;
            for (Book book : dummyData) {
                  if (book.getid() == id) {
                        bookToRemove = book;
                        break;
                  }
            }
            if (bookToRemove != null) {
                  System.out.println("Book removed successfully!" + "\n" + "ID: " + bookToRemove.getid() + "\n"
                              + "Title: " + bookToRemove.getName() + "\n");
                  dummyData.remove(bookToRemove);
            } else {
                  System.out.println("Book with ID " + id + " not found.");
            }
      }

      public static void addNewBook(String title, String author) {
            int newId = nextId++; // Assign the next available ID
            dummyData.add(new Book(newId, title, author, false, null, null, ""));
            System.out.println("Book added successfully!" + "\n" + "ID: " + newId + "\n" + "Title: " + title + "\n");
      }

      public static void printAllBooks() {
            List<Book> books = getAllBooks();

            // Calculate column widths
            int idWidth = 4; // Minimum width for ID column
            int titleWidth = 7; // Minimum width for Title column
            int authorWidth = 6; // Minimum width for Author column

            for (Book book : books) {
                  idWidth = Math.max(idWidth, String.valueOf(book.getid()).length());
                  titleWidth = Math.max(titleWidth, book.getName().length());
                  authorWidth = Math.max(authorWidth, book.getAuthor().length());
            }

            // Print table header
            System.out.println("+-" + repeatString("-", idWidth + 2) + "-+-" +
                        repeatString("-", titleWidth + 2) + "-+-" +
                        repeatString("-", authorWidth + 2) + "-+");
            System.out.printf("| %-" + idWidth + "s | %-" + titleWidth + "s | %-" + authorWidth + "s |\n", "ID",
                        "Title", "Author");
            System.out.println("+-" + repeatString("-", idWidth + 2) + "-+-" +
                        repeatString("-", titleWidth + 2) + "-+-" +
                        repeatString("-", authorWidth + 2) + "-+");

            // Print each book's details in a formatted row
            for (Book book : books) {
                  System.out.printf("| %-" + idWidth + "d | %-" + titleWidth + "s | %-" + authorWidth + "s |\n",
                              book.getid(), book.getName(), book.getAuthor());
            }

            // Print table footer
            System.out.println("+-" + repeatString("-", idWidth + 2) + "-+-" +
                        repeatString("-", titleWidth + 2) + "-+-" +
                        repeatString("-", authorWidth + 2) + "-+");
      }

      public static void printAvailableBooks() {
            List<Book> books = getAllBooks();

            // Filter out rented books
            List<Book> availableBooks = new ArrayList<>();
            for (Book book : books) {
                  if (!book.isRented()) {
                        availableBooks.add(book);
                  }
            }

            // Calculate column widths
            int idWidth = 4; // Minimum width for ID column
            int titleWidth = 7; // Minimum width for Title column
            int authorWidth = 6; // Minimum width for Author column

            for (Book book : availableBooks) {
                  idWidth = Math.max(idWidth, String.valueOf(book.getid()).length());
                  titleWidth = Math.max(titleWidth, book.getName().length());
                  authorWidth = Math.max(authorWidth, book.getAuthor().length());
            }

            // Print table header
            System.out.println("+-" + repeatString("-", idWidth + 2) + "-+-" +
                        repeatString("-", titleWidth + 2) + "-+-" +
                        repeatString("-", authorWidth + 2) + "-+");
            System.out.printf("| %-" + idWidth + "s | %-" + titleWidth + "s | %-" + authorWidth + "s |\n", "ID",
                        "Title", "Author");
            System.out.println("+-" + repeatString("-", idWidth + 2) + "-+-" +
                        repeatString("-", titleWidth + 2) + "-+-" +
                        repeatString("-", authorWidth + 2) + "-+");

            // Print each available book's details in a formatted row
            for (Book book : availableBooks) {
                  System.out.printf("| %-" + idWidth + "d | %-" + titleWidth + "s | %-" + authorWidth + "s |\n",
                              book.getid(), book.getName(), book.getAuthor());
            }

            // Print table footer
            System.out.println("+-" + repeatString("-", idWidth + 2) + "-+-" +
                        repeatString("-", titleWidth + 2) + "-+-" +
                        repeatString("-", authorWidth + 2) + "-+");
      }

      public static void printBooksRentedByUser(String userId) {
            List<Book> books = getAllBooks();

            // Filter out books rented by the specified user
            List<Book> rentedBooks = new ArrayList<>();
            for (Book book : books) {
                  if (book.isRented() && book.getUsername() == userId) {
                        rentedBooks.add(book);
                  }
            }

            // Calculate column widths
            int idWidth = 4; // Minimum width for ID column
            int titleWidth = 7; // Minimum width for Title column
            int authorWidth = 6; // Minimum width for Author column

            for (Book book : rentedBooks) {
                  idWidth = Math.max(idWidth, String.valueOf(book.getid()).length());
                  titleWidth = Math.max(titleWidth, book.getName().length());
                  authorWidth = Math.max(authorWidth, book.getAuthor().length());
            }

            // Print table header
            System.out.println("+-" + repeatString("-", idWidth + 2) + "-+-" +
                        repeatString("-", titleWidth + 2) + "-+-" +
                        repeatString("-", authorWidth + 2) + "-+");
            System.out.printf("| %-" + idWidth + "s | %-" + titleWidth + "s | %-" + authorWidth + "s |\n", "ID",
                        "Title", "Author");
            System.out.println("+-" + repeatString("-", idWidth + 2) + "-+-" +
                        repeatString("-", titleWidth + 2) + "-+-" +
                        repeatString("-", authorWidth + 2) + "-+");

            // Print each rented book's details in a formatted row
            for (Book book : rentedBooks) {
                  System.out.printf("| %-" + idWidth + "d | %-" + titleWidth + "s | %-" + authorWidth + "s |\n",
                              book.getid(), book.getName(), book.getAuthor());
            }

            // Print table footer
            System.out.println("+-" + repeatString("-", idWidth + 2) + "-+-" +
                        repeatString("-", titleWidth + 2) + "-+-" +
                        repeatString("-", authorWidth + 2) + "-+");
      }

      public static void leaseBook(String username, int bookId) {
            for (Book book : dummyData) {
                  if (book.getid() == bookId && !book.isRented()) {
                        book.setRentStatus(true);
                        book.setUsername(username);
                        book.setDateOfRent(LocalDate.now());
                        book.setEnddateOfRent(LocalDate.now().plusDays(7));
                        System.out.println("Book leased successfully!");
                        return;
                  }
            }
            System.out.println("Book not found or already rented.");
      }

      public static void returnBook(int bookId) {
            for (Book book : dummyData) {
                  if (book.getid() == bookId && book.isRented()) {
                        book.setRentStatus(false);
                        book.setUsername(null);
                        book.setDateOfRent(null);
                        book.setEnddateOfRent(null);
                        System.out.println("Book returned successfully!");
                        return;
                  }
            }
            System.out.println("Book not found or already returned.");
      }

      public static List<Book> searchForBook(String keyword) {
            List<Book> matchingBooks = new ArrayList<>();
            for (Book book : dummyData) {
                  if (book.getAuthor().toLowerCase().contains(keyword.toLowerCase()) ||
                              book.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                              String.valueOf(book.getid()).contains(keyword)) {
                        matchingBooks.add(book);
                  }
            }
            return matchingBooks;
      }

      public static void searchAndUpdate(String keyword) {
            List<Book> matchingBooks = BOOKDB.searchForBook(keyword);
            if (!matchingBooks.isEmpty()) {
                  printBookTable(matchingBooks);
            } else {
                  System.out.println("No matching books found.");
            }
      }

      public static void printBookTable(List<Book> matchingBooks) {
            // Calculate column widths
            int idWidth = 4; // Minimum width for ID column
            int titleWidth = 7; // Minimum width for Title column
            int authorWidth = 6; // Minimum width for Author column

            for (Book book : matchingBooks) {
                  idWidth = Math.max(idWidth, String.valueOf(book.getid()).length());
                  titleWidth = Math.max(titleWidth, book.getName().length());
                  authorWidth = Math.max(authorWidth, book.getAuthor().length());
            }

            // Print table header
            System.out.println("+-" + repeatString("-", idWidth + 2) + "-+-" +
                        repeatString("-", titleWidth + 2) + "-+-" +
                        repeatString("-", authorWidth + 2) + "-+");
            System.out.printf("| %-" + idWidth + "s | %-" + titleWidth + "s | %-" + authorWidth + "s |\n", "ID",
                        "Title", "Author");
            System.out.println("+-" + repeatString("-", idWidth + 2) + "-+-" +
                        repeatString("-", titleWidth + 2) + "-+-" +
                        repeatString("-", authorWidth + 2) + "-+");

            // Print each book's details in a formatted row
            for (Book book : matchingBooks) {
                  System.out.printf("| %-" + idWidth + "d | %-" + titleWidth + "s | %-" + authorWidth + "s |\n",
                              book.getid(), book.getName(), book.getAuthor());
            }

            // Print table footer
            System.out.println("+-" + repeatString("-", idWidth + 2) + "-+-" +
                        repeatString("-", titleWidth + 2) + "-+-" +
                        repeatString("-", authorWidth + 2) + "-+");
      }

      private static String repeatString(String str, int times) {
            return new String(new char[times]).replace("\0", str);
      }

}
