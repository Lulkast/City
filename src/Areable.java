import java.util.Set;

public interface Areable {
    double getLength ();
    double getWidth ();
    Set<Damag> getDamag();
    String getCityName();              //как без него, я не придумала((( нужен, чтобы понимать, в какой город нужно заливать улицу/парк из базы
    void setCityName(String cityName); //как без него, я не придумала(((
}
