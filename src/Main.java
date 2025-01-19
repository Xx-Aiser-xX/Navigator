import navigator.Navigator;
import navigator.entities.generate.GenerateRoute;
import navigator.entities.Route;
import navigator.impl.NavigatorImpl;
import struct.List;
import struct.Set;
import struct.impl.ArrayList;
import struct.impl.HashSet;

import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final Navigator nav = new NavigatorImpl();
    private static Set<String> routesPoints = new HashSet<>();
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            nav.addRoute(GenerateRoute.generateRoute());
        }
        routesPoints = GenerateRoute.getPoints();
        int action = 0;
        while (action != 10){
            printMenu();
            System.out.print("номер: ");
            action = in.nextInt();
            in.nextLine();
            switch (action){
                case 1:
                    searchRoutes();
                    break;
                case 2:
                    getFavoriteRoutes();
                    break;
                case 3:
                    getTop5Routes();
                    break;
                case 4:
                    getRouteById();
                    break;
                case 5:
                    chooseRoute();
                    break;
                case 6:
                    existRoute();
                    break;
                case 7:
                    addRoute();
                    break;
                case 8:
                    removeRoute();
                    break;
                case 9:
                    availableRoutes();
                    break;
                case 10:
                    exit();
                    break;
                default:
                    System.out.println("что-то пошло нет, введите ещё раз: ");
            }
        }
    }

    private static void printMenu(){
        System.out.println("__________________________________________________________");
        System.out.println("Выберете действие:");
        System.out.println("1. найти маршруты");
        System.out.println("2. найти все маршруты, проходящие через определённый пункт");
        System.out.println("3. показать топ 5 по популярности маршрутов");
        System.out.println("4. найти мрашрут по id");
        System.out.println("5. воспользоваться маршрутом");
        System.out.println("6. проверить, существует ли маршрут");
        System.out.println("7. добавить маршрут");
        System.out.println("8. удалить маршрут");
        System.out.println("9. показать существующие пункты");
        System.out.println("10. выйти");
        System.out.println("__________________________________________________________");
    }

    private static void addRoute(){
        Route route = inputRoute();
        nav.addRoute(route);
        System.out.println("если вы ввели уникальный набор данных, то маршрут добавлен!");
    }

    private static String inputId(){
        System.out.println("введите id маршрута");
        System.out.println("id должен состоять из 8 букв любого регистра пример: ABCDEFGH");
        String id = in.nextLine();
        while (id.length() != 8) {
            System.out.println("ошибка ввода! попробуйте ещё раз");
            id = in.nextLine();
        }
        return id;
    }

    private static Route inputRoute(){
        String routeId = inputId();
        Route route = new Route(routeId);

        System.out.print("введите расстояние: ");
        double distance = in.nextDouble();
        route.setDistance(distance);

        System.out.print("введите популярность маршрута: ");
        int popularity = in.nextInt();
        in.nextLine();
        route.setPopularity(popularity);

        System.out.println("хотите добавить маршрут в избранное? (да/нет)");
        String favorite = in.nextLine().toLowerCase();
        while (!favorite.equals("да") && !favorite.equals("нет")){
            System.out.println("что-то пошло нет так, повторите попытку");
            System.out.print("хотите добавить маршрут в избранное? (да/нет)");
            favorite = in.nextLine().toLowerCase();
        }
        boolean isFavorite = favorite.equals("да");
        route.setFavorite(isFavorite);

        System.out.println("введите промежуточные пункты маршрута, разделяя запятой и пробелом");
        System.out.println("пример: \"Новочебоксаркс, Чебоксары\"");
        String[] src = in.nextLine().split(", ");
        List<String> points = new ArrayList<>(src.length);
        for (String point : src) {
            points.add(point);
            routesPoints.add(point);
        }
        route.setLocationPoints(points);
        return route;
    }

    private static void removeRoute(){
        String routeId = inputId();
        int check = nav.size();
        nav.removeRoute(routeId);;
        if (check - 1 == nav.size())
            System.out.println("удаёлн маршрут с id: " + routeId);
        else
            System.out.println("маршрут с id:" + routeId + " не был удалён по какой-то причине");
    }

    private static void existRoute(){
        Route route = new Route(inputId());

        System.out.print("введите расстояние: ");
        double distance = in.nextDouble();
        in.nextLine();
        route.setDistance(distance);

        System.out.println("введите начальный пункт: ");
        String startPoint = in.nextLine();
        route.getLocationPoints().add(startPoint);
        System.out.println("введите конечный пункт: ");
        String endPoint = in.nextLine();
        route.getLocationPoints().add(endPoint);

        boolean exist = nav.contains(route);
        if(exist)
            System.out.println("маршрут существует!");
        else
            System.out.println("маршрут не существует!");
    }

    private static void getRouteById(){
        String routeId = inputId();
        Route route = nav.getRoute(routeId);
        System.out.println(route);
    }

    private static void availableRoutes(){
        System.out.println("существующие пункты:");
        for (String routesPoint : routesPoints) {
            System.out.println(routesPoint);
        }
    }

    private static void chooseRoute(){
        String routeId = inputId();
        Route route = nav.getRoute(routeId);
        if (route != null) {
            nav.chooseRoute(routeId);
            System.out.println(route);
        }
        else
            System.out.println("такого маршрута не существует");
    }

    private static void searchRoutes(){
        System.out.print("введите начальный пункт: ");
        String startPoint = in.nextLine();

        System.out.print("введите конечный пункт: ");
        String endPoint = in.nextLine();
        Iterable<Route> routes = nav.searchRoutes(startPoint, endPoint);
        printRoutes(routes);
    }

    private static void exit(){
        System.out.println("завершение работы");
    }

    private static void getFavoriteRoutes(){
        System.out.println("введите промежуточный или конечный пункт назначения: ");
        String point = in.nextLine();
        Iterable<Route> routes = nav.getFavoriteRoutes(point);
        printRoutes(routes);
    }

    private static void printRoutes(Iterable<Route> routes){
        int count = 0;
        System.out.println("результат: ");
        for (Route route : routes) {
            count++;
            System.out.println(route);
        }
        if(count == 0)
            System.out.println("не найдено ни одного совпадения");
    }

    private static void getTop5Routes(){
        Iterable<Route> routes = nav.getTop3Routes();
        printRoutes(routes);
    }
}