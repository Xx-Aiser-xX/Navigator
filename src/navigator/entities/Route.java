package navigator.entities;

import struct.List;
import struct.impl.ArrayList;

public class Route{
    private String id;
    private double distance;
    private int popularity;
    private boolean isFavorite;
    private List<String> locationPoints;

    public Route(String id, int distance, int popularity, boolean isFavorite, List<String> locationPoints) {
        this.id = id;
        this.distance = distance;
        this.popularity = popularity;
        this.isFavorite = isFavorite;
        this.locationPoints = locationPoints;
    }

    public Route(String id) {
        this.id = id;
        this.locationPoints = new ArrayList<>();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public double getDistance() {
        return distance;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getPopularity() {
        return popularity;
    }
    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public boolean isFavorite() {
        return isFavorite;
    }
    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public List<String> getLocationPoints() {
        return locationPoints;
    }
    public void setLocationPoints(List<String> locationPoints) {
        this.locationPoints = locationPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route temp = (Route) o;
        return distance == temp.distance
                && locationPoints.getFirst().equals(temp.locationPoints.getFirst())
                && locationPoints.getLast().equals(temp.locationPoints.getLast());
    }

    @Override
    public int hashCode() {
        if (id == null) {
            return 0;
        }
        int hashSum = 13;
        for (int i = 0; i < id.length(); i++) {
            hashSum = hashSum * 19 + id.charAt(i) * i;
        }
        return hashSum;
    }

    @Override
    public String toString() {
        String points = "";
        int count = 0;
        for (String locationPoint : locationPoints) {
            points += locationPoint;
            if(locationPoints.size() > 1 && count < locationPoints.size() - 1) {
                points += ", ";
                count++;
            }
        }
        return "Маршрут:" + "\n" +
                "id: " + id + "\n" +
                "Расстояние = " + distance + "\n" +
                "Популярность = " + popularity + "\n" +
                "Избранный? " + (isFavorite ? "да" : "нет") + "\n" +
                "Этапы маршрута: " + points + "\n";
    }
}
