package cat.tecnocampus.fgcstations.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class DayTimeStart {
    
    @Id
    private String id;
    private String timeStart;
    private String dayOfWeek;
    
    public DayTimeStart() {
    }
    
    public DayTimeStart(String day, String time, String id) {
        dayOfWeek = day;
        timeStart = time;
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getTimeStart() {
        return timeStart;
    }
    
    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }
    
    public String getDayOfWeek() {
        return dayOfWeek;
    }
    
    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        
        DayTimeStart that = (DayTimeStart) o;
        
        if (!Objects.equals(timeStart, that.timeStart))
            return false;
        return Objects.equals(dayOfWeek, that.dayOfWeek);
    }
    
    @Override
    public int hashCode() {
        int result = timeStart != null ? timeStart.hashCode() : 0;
        result = 31 * result + (dayOfWeek != null ? dayOfWeek.hashCode() : 0);
        return result;
    }
    
    public String getStartTime() {
        return getTimeStart();
    }
    
    public String getDay() {
        return getDayOfWeek();
    }
    
}
