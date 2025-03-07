import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    protected ArrayList<Book> books = new ArrayList<>();
    protected ArrayList<Dvd> dvds = new ArrayList<>();

    public static void main(String[] args) {
        Main main = new Main();

        main.books = Book.readData();
        main.dvds = Dvd.readData();

        System.out.printf("Read %d books and %d dvds!%n%n", main.books.size(), main.dvds.size());

        main.run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);

        int choice = -1;
        while (true) { // [C]ollection, [L]end list, lend [O]ut, take back [I]n or e[X]it: c
            System.out.println("*** Library Selector ***");
            System.out.println(" 1) Show Collection");
            System.out.println(" 2) Lend List");
            System.out.println(" 3) Lend Out");
            System.out.println(" 4) Take back in");
            System.out.println(" 5) Exit Application\n");
            System.out.print("[]: ");

            boolean validInput = false;
            do {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice < 1 || choice > 5) {
                        System.err.print("That is not a valid option! Try again: ");
                    } else {
                        validInput = true;
                    }
                } catch (NumberFormatException e) {
                    System.err.print("That is not a number! Try again: ");
                }
            } while (!validInput);

            switch (choice) {
                case 1:
                    showCollection();
                    break;
                case 2:
                    showLendList();
                    break;
                case 3:
                    lendOut();
                    break;
                case 4:
                    takeBackIn();
                    break;
                case 5:
                    scanner.close();
                    return;
            }
            pause(scanner);
        }
    }

    private void showCollection() {
        System.out.println("\nCollection: ");
        for (Book book : books) {
            System.out.println((book.isLendedOut() ? "*" : " ") + " - " + book.toString());
        }
        for (Dvd dvd : dvds) {
            System.out.println((dvd.isLendedOut() ? "*" : " ") + " - " + dvd.toString());
        }
        System.out.println("|==== END OF LIST ====|\n");
    }

    private void showLendList() {
        System.out.println("\nLended out: ");
        for (Book book : books) {
            if(book.isLendedOut()) {
                System.out.println(" - " + book.toString());
            }
        }
        for (Dvd dvd : dvds) {
            if(dvd.isLendedOut()) {
                System.out.println(" - " + dvd.toString());
            }
        }
        System.out.println("|==== END OF LIST ====|\n");
    }

    private void lendOut() {
        System.out.println("*************** LibraryObject selector ***************");
        int index = 1;
        List<LibraryObject> availableItems = new ArrayList<>();

        for (Book book : books) {
            if (!book.isLendedOut()) {
                availableItems.add(book);
                System.out.printf("%d) - %s%n", index++, book.toString());
            }
        }

        for (Dvd dvd : dvds) {
            if (!dvd.isLendedOut()) {
                availableItems.add(dvd);
                System.out.printf("%d) - %s%n", index++, dvd.toString());
            }
        }

        if (availableItems.isEmpty()) {
            System.out.println("No items available for lending.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nPlease select a value: ");
        int choice = -1;

        boolean validInput = false;
        do {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > availableItems.size()) {
                    System.err.print("That is not a valid option! Try again: ");
                } else {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                System.err.print("That is not a number! Try again: ");
            }
        } while (!validInput);

        LibraryObject selectedItem = availableItems.get(choice - 1);
        if (selectedItem instanceof Book selectedBook) {
            selectedBook.lendOut();
            System.out.println("You have successfully lent out the book: " + selectedBook);
        } else if (selectedItem instanceof Dvd selectedDvd) {
            selectedDvd.lendOut();
            System.out.println("You have successfully lent out the DVD: " + selectedDvd);
        }
    }

    private void takeBackIn() {
        System.out.println("*************** LibraryObject selector ***************");
        int index = 1;
        List<LibraryObject> availableItems = new ArrayList<>();

        for (Book book : books) {
            if (book.isLendedOut()) {
                availableItems.add(book);
                System.out.printf("%d) - %s%n", index++, book.toString());
            }
        }

        for (Dvd dvd : dvds) {
            if (dvd.isLendedOut()) {
                availableItems.add(dvd);
                System.out.printf("%d) - %s%n", index++, dvd.toString());
            }
        }

        if (availableItems.isEmpty()) {
            System.out.println("No items available for taking back in.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nPlease select a value: ");
        int choice = -1;

        boolean validInput = false;
        do {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > availableItems.size()) {
                    System.err.print("That is not a valid option! Try again: ");
                } else {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                System.err.print("That is not a number! Try again: ");
            }
        } while (!validInput);


        LibraryObject selectedItem = availableItems.get(choice - 1);
        if (selectedItem instanceof Book selectedBook) {
            selectedBook.takeBackIn();
            System.out.println("You have successfully taken back in the book: " + selectedBook);
        } else if (selectedItem instanceof Dvd selectedDvd) {
            selectedDvd.takeBackIn();
            System.out.println("You have successfully taken back in the DVD: " + selectedDvd);
        }
    }

    public static void pause(Scanner scanner) {
        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }
}