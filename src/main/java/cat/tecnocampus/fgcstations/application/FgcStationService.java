package cat.tecnocampus.fgcstations.application;

import cat.tecnocampus.fgcstations.application.DTOs.StationDTO;
import cat.tecnocampus.fgcstations.application.DTOs.StationDTOInterface;
import cat.tecnocampus.fgcstations.application.DTOs.StationTopFavoriteJourneyInterface;
import cat.tecnocampus.fgcstations.application.exception.StationDoesNotExistsException;
import cat.tecnocampus.fgcstations.domain.Station;
import cat.tecnocampus.fgcstations.persistence.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FgcStationService {
    
    private final StationRepository stationRepository;
    
    @Autowired
    public FgcStationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }
    
    public List<StationDTOInterface> getStationsDTO() {
        //TODO 1: get all stations (see the returned type)
        return stationRepository.findAllProjectedBy();
    }
    
    public List<Station> getStationsDomain() {
        //TODO 2: get all stations (see you return a domain Station). Actually you don't need to leave this file
        // in order to complete this exercise
        return stationRepository.findAll();
    }
    
    public Station getStation(String name) {
        return stationRepository.findStationByName(name)
                .orElseThrow(() -> new StationDoesNotExistsException("Station with name " + name + " does not exist"));
    }
    
    
    public StationDTOInterface getStationDTO(String name) {
        // TODO 4: get a station by name (see the returned type). If the station does not exist, throw a StationDoesNotExistsException
        Station station = stationRepository.findStationByName(name)
                .orElseThrow(() -> new StationDoesNotExistsException("Station with name " + name + " does not exist"));
        return new StationDTO(station.getName(), station.getLongitud(), station.getLatitud());
    }
    
    public List<StationTopFavoriteJourneyInterface> getStationsOrderedByFavoriteJourneysAsEitherOriginOrDestination() {
        // TODO 5: this is an optional exercise because is quite tricky. You need to use a native query because is no possible to
        //  have a derived table (subquery).
        //  You first need to use a 'UNION' to get the stations either as origin or destination of a Journey. Then you need to group and count
        return null;
    }
}