package cat.tecnocampus.fgcstations.application;

import cat.tecnocampus.fgcstations.application.DTOs.*;
import cat.tecnocampus.fgcstations.application.exception.UserDoesNotExistsException;
import cat.tecnocampus.fgcstations.domain.Friend;
import cat.tecnocampus.fgcstations.domain.User;
import cat.tecnocampus.fgcstations.persistence.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FgcFriendService {
    
    private final FriendRepository friendRepository;
    private final FcgUserService fcgUserService;
    
    @Autowired
    public FgcFriendService(FriendRepository friendRepository, FcgUserService fcgUserService) {
        this.friendRepository = friendRepository;
        this.fcgUserService = fcgUserService;
    }
    
    public UserFriendsDTO getUserFriends(String username) {
        // TODO 20: find all the friends of a user given her username. You can solve this exercise without any sql query
        return null;
    }
    
    public List<UserFriendsDTO> getAllUserFriends() {
        // TODO 21: find all the friends (domain) of all users. You can solve this exercise without leaving this file
        //  note that domain objects are mapped to DTOs
        return null;
    }
    
    public void saveFriends(UserFriendsDTO userFriendsDTO) {
    }
    
    public List<UserTopFriend> getTop3UsersWithMostFriends() {
        // TODO 22: find the top 3 users with the most friends.
        return null;
    }
    
    public List<FriendUserDTOInterface> getUsersByFriend(String friendName) {
        // TODO 23: find all users whose friends have a certain name.
        return null;
    }
}