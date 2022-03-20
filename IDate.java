public interface IDate {
    void deconstructDate(String date) throws Exception;
    int getDay();
    int getMonth();
    int getYear();
}
