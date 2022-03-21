import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Helpers {
    static Scanner in = new Scanner(System.in);

    /**
     * Used to print a line to save time coding and clean up code
     * @param message
     */
    public static void printLine(String message){
        System.out.println(message);
    }

    // Overloading method for optional message parameter
    /**
     * Exits the program
     */
    public static void exitProgram() {
        printLine("");
        System.exit(400);
    }

    /**
     * Exits the program, displaying a message first
     * @param message
     */
    public static void exitProgram(String message) {
        printLine(message);
        printLine("");
        System.exit(400);
    }
    
    /**
     * Returns the line written by user in terminal
     * @return The string that was written by the user
     */
    public static String readLine(){
        return in.nextLine().replace("\"", "");
    }

    /**
     * Given a string, will return a date object of the correct subclass
     * @param dateString
     * @return A Date object created from string value given
     */
    public static Date createDate(String dateString) {
        Date date;
        if (dateString.contains("-") || dateString.contains("/")) {
            date = new NumericalDate(dateString);
        } else {
            date = new LexicalDate(dateString);
        }
        return date;
    }

    /**
     * Reads a file containing dates and returns a list of them
     * @param file
     * @return A Date ArrayList made from values in the file
     */
    public static ArrayList<Date> readFile(String file) {
        ArrayList<Date> dateList = new ArrayList<Date>();

        try {
            FileReader reader = new FileReader(file);
            BufferedReader in = new BufferedReader(reader);

            String s;
            while ((s = in.readLine()) != null) {
                // Remove comma if last character
                if(s.substring(s.length() - 1).equals(",")) s = s.substring(0, s.length() - 1);
                dateList.add(createDate(s));
            }
            in.close();

        } catch (FileNotFoundException e) {
            exitProgram("\nThat file does not exist.");

        } catch (Exception e){
            printLine(e.getMessage());
            exitProgram("\nAn error has occured.");
        }

        return dateList;
    }

    /**
     * Quick way to check if the string was intended to be a date
     * @param dateString
     * @return boolean of whether the string could be a date
     */
    public static boolean checkIntendedDate(String dateString){
        final String FORMATED_INPUT = dateString.toLowerCase();
        return FORMATED_INPUT.contains("-") 
            || FORMATED_INPUT.contains("/")
            || FORMATED_INPUT.contains("st")
            || FORMATED_INPUT.contains("nd")
            || FORMATED_INPUT.contains("rd")
            || FORMATED_INPUT.contains("th");
    }
    
    /**
     * Prints valid date types to prompt user, starting each line with the prefix
     * @param prefix
     */
    public static void printValidDateFormats(String prefix) {
        Helpers.printLine(prefix + "- \"7th March 2022\"");
        Helpers.printLine(prefix + "- \"07/03/2022\" [dd/mm/yyyy]");
        Helpers.printLine(prefix + "- \"07-03-2022\" [dd-mm-yyyy]");
        Helpers.printLine(prefix + "- (Date must be 1753 onwards)");
    }

    /**
     * Given a date, it checks if it is valid. I.e. the month isnt above 12 etc.
     * @param date
     * @return Boolean whether it is a valid date.
     */
    public static boolean isValidDate(Date date) throws Exception {
        final int D = date.getDay();
        final int M = date.getMonth();
        final int Y = date.getYear();

        // Check year
        if (Y < 1753) return false;   
        // Check month 
        if (M > 12) return false;
        
        // Month of 31 days
        if ((M < 8 && M % 2 == 1) || (M >=8 && M % 2 == 0)) {
            if (D > 31) return false;
        }
        // Checking for february
        else if (M == 2){
            // Leap year
            if (((Y % 4 == 0) && (Y % 100 != 0)) || (Y % 400 == 0)) {
                if (D > 29) return false; 
            }
            // Not leap year
            else if (D > 28) return false;
        }
        // Month of 30 days
        else if (D > 30) return false;

        return true;
    }
}
