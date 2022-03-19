public interface IDate {
    int d;
    int m;
    int y;

    void deconstructDate(String date);
    int getDay();
    int getMonth();
    int getYear();
}
