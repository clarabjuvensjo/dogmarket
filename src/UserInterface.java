
/**
 * Clara Bjuvensjö clbj3090
 */

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public void close() {
        scanner.close();
    }

    public int readAge() {
        return readMandatoryInt("Age");
    }

    public int readBidAmount(int minimumBidAmount) {
        int amount;
        String message = "Bid (minimum " + minimumBidAmount + ")";
        do {
            amount = readMandatoryInt(message);
            if (amount < minimumBidAmount) {
                writeError(message);
            }
        } while (amount < minimumBidAmount);
        return amount;
    }

    public String readBreed() {
        return normalize(readMandatoryString("Breed"));
    }

    public String readCommand() {
        return readString("Command", false).toLowerCase();
    }

    public String readDogName() {
        return normalize(readMandatoryString("Dog name"));
    }

    public double readMinimumTailLength() {
        return readMandatoryDouble("Minimum tail length");
    }

    public String readUserName() {
        return normalize(readMandatoryString("User name"));
    }

    public int readWeight() {
        return readMandatoryInt("Weight");
    }

    public void write(Object that) {
        System.out.println(that);
    }

    public void writeError(Object that) {
        System.out.println("Error: " + that);
    }

    private String normalize(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    private void prompt(String message) {
        write(message + ">");
    }

    private double readMandatoryDouble(String message) {
        do {
            try {
                return Double.parseDouble(readMandatoryString(message));
            } catch (NumberFormatException e) {
                writeError(message + " must be a double");
            }
        } while (true);
    }

    private int readMandatoryInt(String message) {
        do {
            try {
                return Integer.parseInt(readMandatoryString(message));
            } catch (NumberFormatException e) {
                writeError(message + " must be an integer");
            }
        } while (true);
    }

    private String readMandatoryString(String message) {
        return readString(message, true);
    }

    private String readString(String message, boolean mandatory) {
        if (mandatory) {
            do {
                prompt(message);
                String s = scanner.nextLine().trim();
                if (s.length() > 0) {
                    return s;
                }
                writeError(message + " is mandatory");
            } while (true);
        }
        prompt(message);
        return scanner.nextLine().trim();
    }
}
