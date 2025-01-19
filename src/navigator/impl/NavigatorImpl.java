package navigator.impl;

import navigator.Navigator;
import navigator.entities.Route;

import java.util.Comparator;

import struct.LinkedList;
import struct.List;
import struct.Set;
import struct.impl.ArrayList;
import struct.impl.HashSet;

public class NavigatorImpl implements Navigator {
    private Set<Route> routes;

    public NavigatorImpl() {
        this.routes = new HashSet<Route>();
    }

    public NavigatorImpl(int capacity) {
        this.routes = new HashSet<Route>(capacity);
    }

    @Override
    public void addRoute(Route route) {
        if (getRoute(route.getId()) == null)
            routes.add(route);
    }

    @Override
    public void removeRoute(String routeId) {
        Route route = getRoute(routeId);
        if (route != null) {
            routes.remove(route);
        }
    }

    @Override
    public boolean contains(Route route) {
        return routes.contains(route);
    }

    @Override
    public int size() {
        return routes.size();
    }

    @Override
    public Route getRoute(String routeId) {
        Route routeIndex = new Route(routeId);
        LinkedList<Route> list = routes.getLinkedListByIndex(routeIndex);
        for (Route route : list){
            if(route.getId().equals(routeId)) {
                return route;
            }
        }
        return null;
    }

    @Override
    public void chooseRoute(String routeId) {
        Route route = getRoute(routeId);
        if (route != null)
            route.setPopularity(route.getPopularity() + 1);
    }

    @Override
    public Iterable<Route> searchRoutes(String startPoint, String endPoint) {
        List<Route> search = new ArrayList<>();
        List<String> points = new ArrayList<>();

        for (Route route : routes) {
            points = route.getLocationPoints();

            if(points.getFirst().equals(startPoint) && points.getLast().equals(endPoint))
                search.add(route);
        }
        sortFavorite(search);
        return search;
    }

    @Override
    public Iterable<Route> getFavoriteRoutes(String destinationPoint) {
        List<Route> search = new ArrayList<>();
        List<String> points;

        for (Route route : routes) {
            points = route.getLocationPoints();
            String startPoint = points.getFirst();

            if(route.isFavorite() && !startPoint.equals(destinationPoint)
                    && points.contains(destinationPoint))
                search.add(route);
        }
        sortFavorite(search);
        return search;
    }

    private void sortFavorite(List<Route> search){
        search.sort(new Comparator<Route>() {
            @Override
            public int compare(Route o1, Route o2) {
                int sort = Boolean.compare(o1.isFavorite(), o2.isFavorite());
                if(sort == 0) {
                    sort = Double.compare(o1.getDistance(), o2.getDistance());
                    if (sort == 0)
                        sort = Integer.compare(o2.getPopularity(), o1.getPopularity());
                }
                return sort;
            }
        });
    }

    @Override
    public Iterable<Route> getTop3Routes() {
        List<Route> search = new ArrayList<>(routes.size());
        for (Route route : routes) {
            search.add(route);
        }
        search.sort(new Comparator<Route>() {
            @Override
            public int compare(Route o1, Route o2) {
                int sort = Integer.compare(o2.getPopularity(), o1.getPopularity());
                if(sort == 0) {
                    sort = Double.compare(o1.getDistance(), o2.getDistance());
                    if (sort == 0)
                        sort = Integer.compare(o1.getLocationPoints().size(),
                                o2.getLocationPoints().size());
                }
                return sort;
            }
        });
        List<Route> topFive = new ArrayList<>();
        int count = 0;
        for (Route route : search){
            if(topFive.add(route))
                count++;
            if (count >= 5)
                break;
        }
        return topFive;
    }
}
