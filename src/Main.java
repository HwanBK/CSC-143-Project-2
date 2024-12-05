import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;


/**
 * Demonstration class.
 *
 * @author Hwansu Kim (Billy)
 * @version 04.26.2022
 */
public class Main {


    /**
     * For demos
     *
     * @param args n/a
     * @throws FileNotFoundException Exception for files.
     */
    public static void main(String[] args) throws FileNotFoundException {
        File nameFile = new File("BabyNames.txt");
        Scanner fileIn = new Scanner(nameFile);
        SortedArrayList<String> babyNames = new SortedArrayList<>();

        while (fileIn.hasNextLine()) {
            babyNames.add(fileIn.nextLine());
        }

        for (int num = 0; num < 10; num++) {
            babyNames.add("Wacky Woohoo Pizza Man");
        }
        System.out.println("indexOf first WWPM: " + babyNames.indexOf("Wacky Woohoo Pizza Man"));
        System.out.println("size: " + babyNames.size() + "\n");

        System.out.println("Iterator Results: ");
        Iterator<String> nameIterator = babyNames.iterator();
        while (nameIterator.hasNext()) {
            String nextName = nameIterator.next();
            System.out.println(nextName);
            if (nextName.equals("Henry")) {
                nameIterator.remove();
            }
        }

        babyNames.remove(0);
        babyNames.remove(0);

        System.out.println("\nArray of names:");
        String[] babyArray = babyNames.toArray(new String[0]);
        for (String aName : babyArray) {
            System.out.println(aName);
        }

        System.out.println("\nTwo param get() results:");
        String [] getArray = babyNames.get("Wacky Woohoo Pizza Man", new String[0]);
        for (String getName : getArray) {
            System.out.println(getName);
        }
    }
}
