import java.util.ArrayList;
import java.io.*;

public class ReadFile {
    public static ArrayList<Date> readFile(String file) {
        ArrayList<Date> dateList = new ArrayList<Date>();

        try {

            FileReader reader = new FileReader(file);
            BufferedReader in = new BufferedReader(reader);

            String s;
            while ((s = in.readLine()) != null) {
                Date date;
                if (s.contains("-") || s.contains("/")) {
                    date = new NumericalDate(s);
                } else {
                    date = new LexicalDate(s);
                }
                dateList.add(date);
            }
            in.close();

        } catch (FileNotFoundException e) {
            Program.exitProgram("That file does not exist.");

        } catch (Exception e){
            Program.exitProgram("An error has occured.");
        }

        return dateList;
    }
}
