import java.util.ArrayList;

public class Program {
    private static void printArgumentsError() {
        Helpers.printLine("\nYou can provide no arguments and follow the instructions, or follow the following rules if you provide one:");
        Helpers.printLine("- If you provide an argument you cannot provide more that one, which must be a file or date,");
        Helpers.printLine("- The text file must contain the extention .txt that includes one date per line,");
        Helpers.printLine("- or the date must be of format:");
        Helpers.printLine("\t- \"7th March 2022\"");
        Helpers.printLine("\t- \"07/03/2022\" [dd/mm/yyyy]");
        Helpers.printLine("\t- \"07-03-2022\" [dd-mm-yyyy]");
    }

    private static void mostFrequentDayFromFile(String file) {
        ArrayList<Date> dateList = Helpers.readFile(file);
        if (dateList.size() > 0) {
            Helpers.printLine("The most frequent day of the week in \"" + file + "\" is: " + DayCalculator.mostFrequentDayOfWeek(dateList));
        } else {
            Helpers.printLine("There were no dates in that file.");
        }
    }

    private static void mostFrequentDateLoop() {
        boolean continueLoop = true;
        Helpers.printLine("Would you like the most frequent date from a list in a text file (1), or provide the dates by writing them (2), or exit (3)? (1-3): ");
        while (continueLoop){
            switch (Helpers.readLine().trim()){
                // Logic for text file
                case "1":
                    Helpers.printLine("What is the name of the text file (.txt): ");
                    String file = Helpers.readLine().trim();
                    if (!file.endsWith(".txt")) {
                        Helpers.printLine("Invalid file type.");
                    } else {
                        mostFrequentDayFromFile(file);
                    }
                    continueLoop = false;
                    break;
                // Logic for writing dates
                case "2":
                    Helpers.printLine("Write out the dates, ending with an empty line: ");
                    ArrayList<Date> dateList = new ArrayList<Date>();
                    while (true) {
                        String input = Helpers.readLine().trim();
                        if (input.equals("")) break;
                        dateList.add(Helpers.createDate(input));
                    }
                    if (dateList.size() > 0) {
                        Helpers.printLine("The most frequent day of the week is: " + DayCalculator.mostFrequentDayOfWeek(dateList));
                    } else {
                        Helpers.printLine("There were no dates provided.");
                    }
                    continueLoop = false;
                    break;
                // Logic for exit program
                case "3":
                    continueLoop = false;
                    break;
                // Logic for invalid input
                default:
                    Helpers.printLine("That wasn't a valid input. Please provide wither 1 or 2. Try again: ");
            }
        }
    }

    private static void printDayOfWeek(String input) {
        Date date = Helpers.createDate(input);
        Helpers.printLine("The day of the week on the " + input + " is: " + DayCalculator.dayOfWeek(date));
    }

    private static void handleNoInput() {
        boolean continuetLoop = true;
        Helpers.printLine("Would you like to work out the most frequent day of the week from a list of dates (1), work out the day of the week for a single one (2), or exit (3)? (1-3): ");
        while (continuetLoop){
            switch (Helpers.readLine().trim()){
                // Logic for most frequent date
                case "1":
                    mostFrequentDateLoop();
                    continuetLoop = false;
                    break;
                // Logic for day of week
                case "2":
                    Helpers.printLine("What date would you like the day of the week for?: ");
                    printDayOfWeek(Helpers.readLine());
                    continuetLoop = false;
                    break;
                // Logic for exit program
                case "3":
                    continuetLoop = false;
                    break;
                // Logic for invalid input
                default:
                    Helpers.printLine("That wasn't a valid input. Please provide either 1-3. Try again: ");
            }

        }
    }

    public static void main(String[] args){
        switch (args.length){
            // Given no inputs the program asks the user what they want, otherwise it assumes what they want.
            case 0:
                handleNoInput();
                break;
            // Given 1 input the program assumes what they want to do
            case 1:
                final String INPUT = args[0].trim();

                // If the user submits a file it assumes they want the most frequent day
                if (INPUT.endsWith(".txt")){
                    mostFrequentDayFromFile(INPUT);
                } 
                // If the user submits a date it assumes they want the day of the week
                else if (Helpers.checkValidDate(INPUT)) {
                    printDayOfWeek(INPUT);
                } 
                // Otherwise it is an invalid input and the program informs the user
                else {
                    Helpers.printLine("That was not a valid input.\n");
                    printArgumentsError();
                }
                break;
            // Given more than one input the program prints an error.
            default:
                printArgumentsError();
        }
    }
}