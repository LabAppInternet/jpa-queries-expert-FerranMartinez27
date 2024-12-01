package cat.tecnocampus.fgcstations.application.DTOs;

public class StationTopFavoriteJourney implements StationTopFavoriteJourneyInterface {
    
    private String name;
    private int numberOfFavoriteJourneys;
    
    public StationTopFavoriteJourney(String name, int numberOfFavoriteJourneys) {
        this.name = name;
        this.numberOfFavoriteJourneys = numberOfFavoriteJourneys;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public int getNumberOfFavoriteJourneys() {
        return numberOfFavoriteJourneys;
    }
    
}
