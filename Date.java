public abstract class Date implements IDate {
    private int d;
    private int m;
    private int y;

    public void Date(String date) {
        deconstructDate(date);
    }

    abstract public void deconstructDate(String date);

    public int getDate() {
        return d;
    }

    public int getMonth() {
        return m;
    }

    public int getYear(){
        return y;
    }
}
