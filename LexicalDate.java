public class LexicalDate extends Date {
    public LexicalDate(String date) {
        super(date);
    }

    public void deconstructDate(String date) {
        final String[] MONTHS = {"january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december"};
        final String[] DATE_STRINGS = date.trim().split(" ");

        try {
            if (DATE_STRINGS.length != 3){
                throw new Exception();
            }
            d = Integer.parseInt(DATE_STRINGS[0].replaceAll("\\D+",""));

            int monthIndex = -1;
            for (int i = 1; i < 13; i++) {
                if (MONTHS[i - 1].equals(DATE_STRINGS[1].toLowerCase())){
                    monthIndex = i;
                    break;
                }
            }
            if (monthIndex == -1) throw new Exception();
            m = monthIndex;

            y = Integer.parseInt(DATE_STRINGS[2]);

        } catch (Exception e) {
            Helpers.printLine("\nThere was an incorrect date type provided, the date must one of the following formats:");
            Helpers.printValidDateFormats("");
            Helpers.exitProgram();
        }
    }
}
