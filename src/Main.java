import java.io.IOException;
import java.util.ArrayList;
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
                    System.out.println(3);
                    break;
                case 4:
                    System.out.println(4);
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

    public static void pause(Scanner scanner) {
        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }
}