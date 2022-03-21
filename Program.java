import java.util.ArrayList;

public class Program {

    private static void printArgumentsError() {
        Helpers.printLine("\nYou can provide no arguments and follow the instructions, or follow these rules if you provide one:");
        Helpers.printLine("- If you provide an argument you cannot provide more that one, which must be a file or date,");
        Helpers.printLine("- The text file must contain the extension .txt that includes one date per line,");
        Helpers.printLine("- or the date must be of format:");
        Helpers.printValidDateFormats("\t");
    }

    // Prints most frequent date from file
    private static void printDates(String file) {
        ArrayList<Date> dateList = Helpers.readFile(file);
        if (dateList.size() > 0) {
            Helpers.printLine("\nThe most frequent day of the week in \"" + file + "\" is: " + DayCalculator.mostFrequentDayOfWeek(dateList));
        } else {
            Helpers.printLine("\nThere were no dates in that file.");
        }
    }

    private static void printDayOfWeek(String input) {
        Date date = Helpers.createDate(input);
        Helpers.printLine("\nThe day of the week on the " + input + " is: " + DayCalculator.dayOfWeek(date));
    }

    private static void mostFrequentDateLoop() {
        boolean continueLoop = true;
        Helpers.printLine("\nWould you like the most frequent date from a list in a text file (1), provide the dates by writing them (2), or exit (3)? (1-3): ");
        while (continueLoop){
            switch (Helpers.readLine().trim()){
                // Logic for text file
                case "1":
                    Helpers.printLine("\nWhat is the name of the text file (.txt): ");
                    String file = Helpers.readLine().trim();
                    if (!file.endsWith(".txt")) {
                        Helpers.printLine("\nInvalid file type. It must be a text file (.txt).");
                    } else {
                        printDates(file);
                    }
                    continueLoop = false;
                    break;
                // Logic for writing dates
                case "2":
                    Helpers.printLine("\nWrite out the dates, ending with an empty line: ");
                    ArrayList<Date> dateList = new ArrayList<Date>();
                    while (true) {
                        final String INPUT = Helpers.readLine().trim();
                        if (INPUT.equals("")) break;
                        dateList.add(Helpers.createDate(INPUT));
                    }
                    if (dateList.size() > 0) {
                        Helpers.printLine("\nThe most frequent day of the week is: " + DayCalculator.mostFrequentDayOfWeek(dateList));
                    } else {
                        Helpers.printLine("\nThere were no dates provided.");
                    }
                    continueLoop = false;
                    break;
                // Logic for exit program
                case "3":
                    continueLoop = false;
                    break;
                // Logic for invalid input
                default:
                    Helpers.printLine("\nThat wasn't a valid input. Please provide wither 1 or 2. Try again: ");
            }
        }
    }

    private static void noInputLoop() {
        boolean continueLoop = true;
        Helpers.printLine("\nWould you like to work out the most frequent day of the week from a list of dates (1), work out the day of the week for a single one (2), or exit (3)? (1-3): ");
        while (continueLoop){
            switch (Helpers.readLine().trim()){
                // Logic for most frequent date
                case "1":
                    mostFrequentDateLoop();
                    continueLoop = false;
                    break;
                // Logic for day of week
                case "2":
                    Helpers.printLine("\nWhat date would you like the day of the week for?: ");
                    printDayOfWeek(Helpers.readLine());
                    continueLoop = false;
                    break;
                // Logic for exit program
                case "3":
                    continueLoop = false;
                    break;
                // Logic for invalid input
                default:
                    Helpers.printLine("\nThat wasn't a valid input. Please provide either 1-3. Try again: ");
            }

        }
    }

    public static void main(String[] args){
        switch (args.length){
            // Given no inputs the program asks the user what they want, otherwise it assumes what they want.
            case 0:
                noInputLoop();
                break;
            // Given 1 input the program assumes what they want to do
            case 1:
                final String INPUT = args[0].trim();

                // If the user submits a file it assumes they want the most frequent day
                if (INPUT.endsWith(".txt")){
                    printDates(INPUT);
                } 
                // If the user submits a date it assumes they want the day of the week
                else if (Helpers.checkIntendedDate(INPUT)) {
                    printDayOfWeek(INPUT);
                } 
                // Otherwise it is an invalid input and the program informs the user
                else {
                    Helpers.printLine("\nThat was not a valid input.");
                    printArgumentsError();
                }
                break;
            // Given more than one input the program prints an error.
            default:
                printArgumentsError();
        }
        Helpers.printLine("");
    }
}