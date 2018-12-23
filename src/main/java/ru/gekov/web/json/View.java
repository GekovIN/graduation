package ru.gekov.web.json;

public class View {
    public interface  JsonRestaurantsWithMenu {}
    public interface  JsonMenuWithRestaurants {}

    public interface JsonRestaurantsWithVote {}

    // For User fields (register date, roles, etc.) showing only in profiles options
    public interface JsonProfile {}
}
