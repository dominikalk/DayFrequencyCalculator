import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Helpers {
    static Scanner in = new Scanner(System.in);

    public static void printLine(String s){
        System.out.println(s);
    }

    public static void exitProgram(String message) {
        printLine(message);
        System.exit(400);
    }
    
    public static String readLine(){
        return in.nextLine().replace("\"", "");
    }

    public static Date createDate(String input) {
        Date date;
        if (input.contains("-") || input.contains("/")) {
            date = new NumericalDate(input);
        } else {
            date = new LexicalDate(input);
        }
        return date;
    }

    public static ArrayList<Date> readFile(String file) {
        ArrayList<Date> dateList = new ArrayList<Date>();

        try {
            FileReader reader = new FileReader(file);
            BufferedReader in = new BufferedReader(reader);

            String s;
            while ((s = in.readLine()) != null) {
                dateList.add(createDate(s));
            }
            in.close();

        } catch (FileNotFoundException e) {
            exitProgram("That file does not exist.");

        } catch (Exception e){
            exitProgram("An error has occured.");
        }

        return dateList;
    }

    public static boolean checkValidDate(String input){
        final String FORMATED_INPUT = input.toLowerCase();
        return FORMATED_INPUT.contains("-") 
            || FORMATED_INPUT.contains("/")
            || FORMATED_INPUT.contains("st")
            || FORMATED_INPUT.contains("nd")
            || FORMATED_INPUT.contains("rd")
            || FORMATED_INPUT.contains("th");
    }
}
