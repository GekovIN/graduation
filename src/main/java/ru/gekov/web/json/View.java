package ru.gekov.web.json;

public class View {
    public interface  JsonRestaurantsWithMenu {}
    public interface  JsonMenuWithRestaurants {}

    public interface JsonRestaurantsWithVote {}

//  https://stackoverflow.com/questions/34535599/spring-and-jsonview-with-multiple-identifiers
    public interface JsonRestaurantsWithMenuAndVotes extends JsonRestaurantsWithMenu, JsonRestaurantsWithVote {}

    // For User fields (register date, roles, etc.) showing only in profiles options
    public interface JsonProfile {}
}
