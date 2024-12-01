package cat.tecnocampus.fgcstations.application.DTOs;

public class UserTopFavoriteJourney implements UserTopFavoriteJourneyInterface{
    
    String username;
    String name;
    String secondName;
    String email;
    int numberOfFavoriteJourneys;
    
    public UserTopFavoriteJourney(String username, String name, String secondName, String email, int size) {
        this.username = username;
        this.name = name;
        this.secondName = secondName;
        this.email = email;
        this.numberOfFavoriteJourneys = size;
    }
    
    @Override
    public String getUsername() {
        return "";
    }
    
    @Override
    public String getName() {
        return "";
    }
    
    @Override
    public String getSecondName() {
        return "";
    }
    
    @Override
    public String getEmail() {
        return "";
    }
    
    @Override
    public int getNumberOfFavoriteJourneys() {
        return 0;
    }
    
}
