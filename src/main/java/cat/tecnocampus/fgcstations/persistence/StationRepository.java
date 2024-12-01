package cat.tecnocampus.fgcstations.persistence;

import cat.tecnocampus.fgcstations.application.DTOs.StationDTOInterface;
import cat.tecnocampus.fgcstations.application.DTOs.StationTopFavoriteJourneyInterface;
import cat.tecnocampus.fgcstations.domain.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StationRepository extends JpaRepository<Station, String> {
    List<StationDTOInterface> findAllProjectedBy();
    Optional<Station> findStationByName(String name);
}