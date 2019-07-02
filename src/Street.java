import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class Street implements Areable, LocatedInTheCity {

    private final UUID id;
    private String name;
    private double length;
    private double width;
    private Set<Damag> damages;
    private String cityName;


    public Street(String name, double length, double width) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.length = length;
        this.width = width;
        this.damages = new HashSet<>();
    }

    public Street(UUID uuid, String name, double length, double width) {    //второй конструктор, потому что в базу к нам поступают улицы с готовыми айди
        this.id = uuid;
        this.name = name;
        this.length = length;
        this.width = width;
        this.damages = new HashSet<>();
    }

    public void setDamag(Damag... damag) {
        for (Damag d : damag
        ) {
            this.damages.add(d);
        }
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getName() {
        return name;
    }

    @Override
    public double getLength() {
        return length;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public Set<Damag> getDamag() {
        return damages;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Street{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", length=" + length +
                ", width=" + width +
                ", damag=" + damages +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Street street = (Street) o;
        return Objects.equals(id, street.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}