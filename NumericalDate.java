public class NumericalDate extends Date {
    public NumericalDate(String date) {
        super(date);
    }

    public void deconstructDate(String date) {
        final String[] dateStrings = date.trim().split("[-/]");

        try {
            if (dateStrings.length != 3){
                throw new Exception();
            }

            d = Integer.parseInt(dateStrings[0]);
            m = Integer.parseInt(dateStrings[1]);
            y = Integer.parseInt(dateStrings[2]);

        } catch (Exception e) {
            Helpers.exitProgram("There was an incorrect date type provided.");
        }
    }
}
