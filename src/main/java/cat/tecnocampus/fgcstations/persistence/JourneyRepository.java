package cat.tecnocampus.fgcstations.persistence;

import cat.tecnocampus.fgcstations.domain.Journey;
import cat.tecnocampus.fgcstations.domain.JourneyId;
import cat.tecnocampus.fgcstations.domain.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.lang.ScopedValue;
import java.util.Optional;

public interface JourneyRepository extends JpaRepository<Journey, JourneyId> {
    
    Optional<Journey> findByOriginNameAndDestinationName(String origin, String destination);
    
}
