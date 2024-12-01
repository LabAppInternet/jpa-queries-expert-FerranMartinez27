package cat.tecnocampus.fgcstations.application.DTOs;

public record UserDTOnoFJ(String username, String name, String secondName, String email) {
    
    public String getUsername() {
        return username;
    }
    
    public String getName() {
        return name;
    }
    
    public String getSecondName() {
        return secondName;
    }
    
    public String getEmail() {
        return email;
    }
}
