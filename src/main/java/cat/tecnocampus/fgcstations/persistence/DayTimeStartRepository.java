package cat.tecnocampus.fgcstations.persistence;

import cat.tecnocampus.fgcstations.application.DTOs.PopularDayOfWeek;
import cat.tecnocampus.fgcstations.domain.DayTimeStart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DayTimeStartRepository extends JpaRepository<DayTimeStart, String> {
    
    List<PopularDayOfWeek> findMostPopularDays();
    
}
