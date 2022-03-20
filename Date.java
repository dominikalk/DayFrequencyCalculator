public abstract class Date implements IDate {
    protected int d;
    protected int m;
    protected int y;

    public Date(String date) {
        deconstructDate(date);
    }

    abstract public void deconstructDate(String date);

    public int getDay() {
        return d;
    }

    public int getMonth() {
        return m;
    }

    public int getYear(){
        return y;
    }
}
