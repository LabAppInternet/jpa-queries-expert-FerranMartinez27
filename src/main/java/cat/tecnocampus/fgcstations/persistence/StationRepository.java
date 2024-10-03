package cat.tecnocampus.fgcstations.persistence;

import cat.tecnocampus.fgcstations.domain.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StationRepository extends JpaRepository<Station, String> {
    public List<Station> getAllStations();
    public Station getStationByName(String name);
}