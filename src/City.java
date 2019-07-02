import java.util.*;

public class City {

    final private Set<Street> streets;
    final private Set<Park> parks;
    final private Set<Areable> allBildings;
    private String name;


    public City(String name) {
        this.name = name;
        this.streets = new HashSet<>();
        this.parks = new HashSet<>();
        this.allBildings = new HashSet<>();
    }

    public void AddStreetToCity(Street street) {
        this.getStreets().add(street);
        this.getAllBildings().add(street);
    }

    public void AddParkToCity(Park park) {
        this.getParks().add(park);
        this.getAllBildings().add(park);
    }

    public String getName() {
        return name;
    }

    public Set<Street> getStreets() {
        return streets;
    }

    public Set<Park> getParks() {
        return parks;
    }

    public Set<Areable> getAllBildings() {
        return allBildings;
    }

    public City setAllBildings(List<LocatedInTheCity> list) {
        list.stream()
                .forEach(a -> {
                    if (a.getClass().toString().equals("class Street")) AddStreetToCity((Street) a);
                    else if (a.getClass().toString().equals("class Park")) AddParkToCity((Park) a);
                });
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return name.equals(city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

