import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public final class Manager {
    private Manager() {
    }

    static Set<City> cities = new HashSet<>();

    static Areable paramsToAreable(List<String> list) {
        Areable areable = null;
        if (list.get(1).equals("Street")) {
            areable = new Street(list.get(3), Double.parseDouble(list.get(4)), Double.parseDouble(list.get(5)));
        } else if (list.get(1).equals("Park")) {
            areable = new Park(list.get(3), Double.parseDouble(list.get(4)), Double.parseDouble(list.get(5)));
        }
        areable.setCityName(list.get(0));
        return areable;
    }

    static List<String> parsingParamsFromText(String text) {
        return Arrays.asList(text.split(",")).stream()
                .map(param -> param.substring(param.indexOf(":") + 1))
                .collect(Collectors.toList());
    }

    static Set<City> getFromBase(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        return reader.lines()
                .map(a -> parsingParamsFromText(a))                                                                //тут мы получили лист стрингов с полями для каждого строения
                .map(a -> paramsToAreable(a))                                                                            //этот метод сделал нужные строения и вернул их как Areable
                .collect(Collectors.groupingBy(Areable::getCityName))                                                    //тут мы сгруппировали Areable по городам, в которых они должны лежать, получили мапу
                .entrySet().stream()
                .map(a -> new City(a.getKey()).setAllBildings(a.getValue()))                                             //из каждой пары (название города, лист со строениями) делаем город с улицами, парками
                .collect(Collectors.toSet());
    }

    public static void main(String[] args) throws IOException {
        cities = getFromBase("C:\\practice\\CityManager\\src\\base.txt");
        cities.stream()
                .forEach(a -> System.out.println(a.getName() + " " + a.getStreets()));
        cities.stream()
                .forEach(a -> System.out.println(a.getName() + " " + a.getParks()));

    }
}