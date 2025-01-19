package navigator.entities.generate;

import navigator.entities.Route;
import struct.List;
import struct.Set;
import struct.impl.ArrayList;
import struct.impl.HashSet;

import java.util.Random;

public class GenerateRoute {
    private static Random random = new Random();
    private static String[] city = new String[]{"Новочебоксаркс", "Чебоксары", "Марий Эл", "Канаш",
            "Батырево", "Алатырь", "Ульяновск", "Казань", "Нижний новгород", "Москва",
            "Питер", "Тюмень", "Старый оскол", "Омск", "Краснодар", "Анапа", "Челябинск",
            "Владимир", "Рязань", "Тамбов", "Кострома"};//21
    
    public static Set<Route> generateData(int count){
        Set<Route> routes = new HashSet<>();
        for(int i = 0; i < count; i++){
            routes.add(generateRoute());
        }
        return routes;
    }

    public static Route generateRoute(){
        Route route = new Route(
                randomRoad(),
                random.nextInt(5, 3000),
                random.nextInt(0, 20),
                random.nextInt(0, 20) < 4,
                getRandomPoints(random.nextInt(2, 5))
        );
        return route;
    }

    public static Set<String> getPoints(){
        Set<String> points = new HashSet<>();
        for (String point : city) {
            points.add(point);
        }
        return points;
    }

    private static String randomRoad(){
        String id = "";
        for (int i = 0; i < 8; i++){
            id += (char) random.nextInt(65, 91);
        }
        return id;
    }

    private static List<String> getRandomPoints(int len){
        List<String> points = new ArrayList<>();
        for (int i = 0; i < len; i++){
            points.add(city[random.nextInt(0, 21)]);
        }
        return points;
    }
}
