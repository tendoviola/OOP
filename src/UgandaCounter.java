import java.util.Scanner;

public class UgandaCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter book description:");
        String description = scanner.nextLine();

        // Convert description to lowercase to ignore case differences
        String lowerDesc = description.toLowerCase();

        // Word to search for
        String target = "uganda";

        int count = 0;
        int index = 0;

        // Loop to find all occurrences of "uganda"
        while ((index = lowerDesc.indexOf(target, index)) != -1) {
            count++;
            index += target.length(); // Move index forward to avoid counting overlapping occurrences
        }

        System.out.println("The word 'Uganda' appears " + count + " times.");

        scanner.close();
    }
}