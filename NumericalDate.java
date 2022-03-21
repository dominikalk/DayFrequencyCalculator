public class NumericalDate extends Date {
    public NumericalDate(String date) {
        super(date);
    }

    public void deconstructDate(String date) {
        final String[] DATE_STRINGS = date.trim().split("[-/]");

        try {
            if (DATE_STRINGS.length != 3){
                throw new Exception();
            }
            
            d = Integer.parseInt(DATE_STRINGS[0]);
            m = Integer.parseInt(DATE_STRINGS[1]);
            y = Integer.parseInt(DATE_STRINGS[2]);

            if (!Helpers.isValidDate(this)) throw new Exception();

        } catch (Exception e) {
            Helpers.printLine(date);
            Helpers.printLine("\nThere was an incorrect date type provided, (" + date + "), the date must one of the following formats:");
            Helpers.printValidDateFormats("");
            Helpers.exitProgram();
        }
    }
}
