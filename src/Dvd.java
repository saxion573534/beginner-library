import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dvd extends LibraryObject{
    //id;title;media;studio;sound;versions;rating;year;genre;aspect;upc
    private final int id;
    private final String title;
    private final String media;
    private final String studio;
    private final String sound;
    private final String versions;
    private final String rating;
    private final int year;
    private final String genre;
    private final String aspect;
    private final long upc;

    private boolean isLendedOut;

    public Dvd(int id, String title, String media, String studio, String sound, String versions, String rating, int year, String genre, String aspect, long upc) {
        this.id = id;
        this.title = title;
        this.media = media;
        this.studio = studio;
        this.sound = sound;
        this.versions = versions;
        this.rating = rating;
        this.year = year;
        this.genre = genre;
        this.aspect = aspect;
        this.upc = upc;

        this.isLendedOut = false;
    }

    public boolean isLendedOut() {
        return isLendedOut;
    }

    public void lendOut() {
        isLendedOut = true;
    }

    public void takeBackIn() {
        isLendedOut = false;
    }

    public static ArrayList<Dvd> readData() {
        ArrayList<Dvd> dvds = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("resources/dvds.csv"));
            scanner.nextLine();

            while (scanner.hasNext()) {
                String[] result = scanner.nextLine().split(";");
                Dvd dvd = new Dvd(Integer.parseInt(result[0]), result[1], result[2], result[3], result[4], result[5], result[6], Integer.parseInt(result[7]), result[8], result[9], Long.parseLong(result[10]));
                dvds.add(dvd);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        return dvds;
    }

    @Override
    public String toString() {
        return "Dvd -> %s (%d), rating: %s, year: %d".formatted(this.title, this.id, this.rating, this.year);
    }
}
