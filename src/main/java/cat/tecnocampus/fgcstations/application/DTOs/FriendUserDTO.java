package cat.tecnocampus.fgcstations.application.DTOs;

import cat.tecnocampus.fgcstations.domain.Friend;

public class FriendUserDTO implements FriendUserDTOInterface{
    
    private String username;
    private String name;
    
    public FriendUserDTO(Friend friend) {
    }
    
    @Override
    public String getUserUsername() {
        return username;
    }
    
    @Override
    public String getUserName() {
        return name;
    }
    
}
