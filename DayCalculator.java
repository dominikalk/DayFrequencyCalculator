import java.util.ArrayList;
import java.util.Arrays;
public class DayCalculator {
    final private static String[] daysOfTheWeek = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

    /**
     * Used to get the day of the week from the date given
     * @param date
     * @return The day of the week of the date
     */
    public static String dayOfWeek( Date date ) {
        int y = date.getYear();
        int m = date.getMonth();
        int d = date.getDay();

        if(m < 3){
            m += 12;
            y -= 1;
        }
        int C = y % 100;
        int D = Math.floorDiv(y, 100);
        int W = 13 * (m + 1) / 5;
        int X = C / 4;
        int Y = D / 4;
        int Z = W + X + Y + d + C - 2 * D;
        int day = Z % 7;
        if(day < 0){
            day += 7;
        }
        return daysOfTheWeek[day];
    }

    /**
     * Used to get the most frequent day of the week from a list of dates
     * @param dates
     * @return The most frequent day of the week in the list of dates
     */
    public static String mostFrequentDayOfWeek( ArrayList<Date> dates ) {
        if(dates.size() == 0) return null;
        int[] dayNumbers = new int[7];

        // Set array dayNumbers with corresponding indexes to day with frequency of day
        for (Date date : dates) {
            int dateIndex = Arrays.asList(daysOfTheWeek).indexOf(dayOfWeek(date));
            dayNumbers[dateIndex] += 1;
        }

        // Iterate through dayNumbers to work out which is the most frequent
        // Start with index 2 so in the case frequencies match the earlier day is returned
        int maxIndex = 2;
        for (int i = 2; i < 9; i++) {
            int index = i;
            if (i >= 7) {
                index -= 7;
            }

            if (dayNumbers[index] > dayNumbers[maxIndex]){
                maxIndex = index;
            }
        }

        return daysOfTheWeek[maxIndex];
    }
}
