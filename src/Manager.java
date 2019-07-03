import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

import static javafx.scene.input.KeyCode.L;


public final class Manager {
    private Manager() {
    }

    static Set<City> cities = new HashSet<>();

   static City buildCityReflectively (String cityName) throws IllegalAccessException, InvocationTargetException, InstantiationException {
       Class <City> cityClass = City.class;
       Constructor <?> [] declaredConstructors = cityClass.getDeclaredConstructors();
       City newCity = (City) declaredConstructors [0].newInstance(cityName);
       return newCity;
   }

   static LocatedInTheCity paramsToLocatedInTheCityReflectively (List <String> list) throws IllegalAccessException, InvocationTargetException, InstantiationException{
      LocatedInTheCity locatedInTheCity = null;
       if (list.get(1).equals("Street")) {
           Class <Street> streetClass = Street.class;
           Constructor <?> [] declaredConstructors = streetClass.getDeclaredConstructors();
           locatedInTheCity = (Street) declaredConstructors [0].newInstance(list.get(3), Double.parseDouble(list.get(4)), Double.parseDouble(list.get(5)));
       } else if (list.get(1).equals("Park")) {
           Class <Park> parkClass = Park.class;
           Constructor <?> [] declaredConstructors = parkClass.getDeclaredConstructors();
           locatedInTheCity = (Park) declaredConstructors [0].newInstance(list.get(3), Double.parseDouble(list.get(4)), Double.parseDouble(list.get(5)));
       }
       locatedInTheCity.setCityName(list.get(0));
       return locatedInTheCity;
   }

    static LocatedInTheCity paramsToLocatedInTheCity(List<String> list) {
        LocatedInTheCity locatedInTheCity = null;
        if (list.get(1).equals("Street")) {
            locatedInTheCity = new Street(list.get(3), Double.parseDouble(list.get(4)), Double.parseDouble(list.get(5)));
        } else if (list.get(1).equals("Park")) {
            locatedInTheCity = new Park(list.get(3), Double.parseDouble(list.get(4)), Double.parseDouble(list.get(5)));
        }
        locatedInTheCity.setCityName(list.get(0));
        return locatedInTheCity;
    }

    static List<String> parsingParamsFromText(String text) {
        return Arrays.asList(text.split(",")).stream()
                .map(param -> param.substring(param.indexOf(":") + 1))
                .collect(Collectors.toList());
    }

    static Set<City> getFromBase(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        return reader.lines()
                .map(Manager::parsingParamsFromText)                                                                //тут мы получили лист стрингов с полями для каждого строения
                .map(Manager::paramsToLocatedInTheCity)                                                                            //этот метод сделал нужные строения и вернул их как Areable
                .collect(Collectors.groupingBy(LocatedInTheCity::getCityName))                                                    //тут мы сгруппировали Areable по городам, в которых они должны лежать, получили мапу
                .entrySet().stream()
                .map(a -> new City(a.getKey()).setAllBildings(a.getValue()))                                             //из каждой пары (название города, лист со строениями) делаем город с улицами, парками
                .collect(Collectors.toSet());
    }

    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, InvocationTargetException {
        cities = getFromBase("C:\\practice\\City\\src\\base.txt");
        cities.stream()
                .forEach(a -> System.out.println(a.getName() + " " + a.getStreets()));
        cities.stream()
                .forEach(a -> System.out.println(a.getName() + " " + a.getParks()));

        // reflection

        City dresden = Manager.buildCityReflectively("Dresden");
        List <String> list = List.of("Dresden", "Street", "1ac301e9-c16a-4873-805c-a890baa93970", "St.Perersburg st", "10", "1000");
        Street street = (Street) Manager.paramsToLocatedInTheCityReflectively(list);
        dresden.AddStreetToCity(street);
        System.out.println(dresden.getStreets());
    }
}