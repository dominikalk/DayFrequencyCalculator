import java.util.ArrayList;

public class Program {

    private static void printLine(String s){
        System.out.println(s);
    }

    public static void exitProgram(String message) {
        printLine(message);
        System.exit(400);
    }

    private static void printArgumentsError() {
        printLine("\nYou must provide one and only one argument of either a file or date:");
        printLine("- The file must contain the extention .txt that includes one date per line,");
        printLine("- or the date must be of format:");
        printLine("\t- \"7th March 2022\"");
        printLine("\t- \"07/03/2022\" [dd/mm/yyyy]");
        printLine("\t- \"07-03-2022\" [dd-mm-yyyy]");
    }

    private static void mostFrequentDayFromFile(String file) {
        ArrayList<Date> dateList = ReadFile.readFile(file);
        printLine(DayCalculator.mostFrequentDayOfWeek(dateList));
    }

    public static void main(String[] args){
        switch (args.length){
            case 0:
                printArgumentsError();
                break;
            case 1:
                final String INPUT = args[0].trim();
                if (INPUT.contains(".txt")){
                    mostFrequentDayFromFile(INPUT);
                } else if (INPUT.contains("-") || INPUT.contains("/")) {
                    Date date = new NumericalDate(INPUT);
                    printLine(DayCalculator.dayOfWeek(date));
                } else {
                    Date date = new LexicalDate(INPUT);
                    printLine(DayCalculator.dayOfWeek(date));
                }
                break;
            default:
                printArgumentsError();
        }
    }
}