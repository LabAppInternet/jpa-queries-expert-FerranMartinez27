package cat.tecnocampus.fgcstations.application.mapper;

import cat.tecnocampus.fgcstations.application.DTOs.DayTimeStartDTO;
import cat.tecnocampus.fgcstations.application.DTOs.FavoriteJourneyDTO;
import cat.tecnocampus.fgcstations.application.DTOs.UserDTO;
import cat.tecnocampus.fgcstations.application.DTOs.UserFriendsDTO;
import cat.tecnocampus.fgcstations.domain.FavoriteJourney;
import cat.tecnocampus.fgcstations.domain.Friend;
import cat.tecnocampus.fgcstations.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class MapperHelper {
    
    public static UserDTO userToUserDTO(User user) {
        var favoriteJourneyDTOList = user.getFavoriteJourneyList().stream().map(fj -> favoriteJourneyToFavoriteJourneyDTO(fj)).toList();
        UserDTO userDTO = new UserDTO(user.getUsername(), user.getName(), user.getSecondName(), user.getEmail(), favoriteJourneyDTOList);
        return userDTO;
    }
    
    public static FavoriteJourneyDTO favoriteJourneyToFavoriteJourneyDTO(FavoriteJourney favoriteJourney) {
        FavoriteJourneyDTO favoriteJourneyDTO = new FavoriteJourneyDTO();
        favoriteJourneyDTO.setDestination(favoriteJourney.getJourney().getDestination().getName());
        favoriteJourneyDTO.setOrigin(favoriteJourney.getJourney().getOrigin().getName());
        favoriteJourneyDTO.setId(favoriteJourney.getId());
        favoriteJourneyDTO.setDayTimes(favoriteJourney.getDayTimeStarts().stream().map(s -> new DayTimeStartDTO(s.getDayOfWeek(), s.getTimeStart())).toList());
        return favoriteJourneyDTO;
    }
    
    public static List<UserFriendsDTO> allUserFriendListToListUserFriendsDTO(List<Friend> friends) {
        Map<String, List<Friend>> friendsMap = friends.stream().collect(groupingBy(Friend::getUsername));
        List<UserFriendsDTO> result = new ArrayList<>(friendsMap.size());
        friendsMap.keySet().stream().forEach(u -> result.add(MapperHelper.listOfAUserFriendsToUserFriendsDTO(friendsMap.get(u))));
        return result;
    }
    
    public static UserFriendsDTO listOfAUserFriendsToUserFriendsDTO(List<Friend> friends) {
        UserFriendsDTO uf = new UserFriendsDTO();
        uf.setUsername(friends.get(0).getUsername());
        uf.setFriends(friends.stream().map(Friend::getFriend).toList());
        return uf;
    }
    
    public static List<Friend> friendsDTOToUserListOfFriends(User user, UserFriendsDTO userFriendsDTO) {
        return userFriendsDTO.getFriends().stream().map(f -> new Friend(user, f)).toList();
    }
    
    public static FavoriteJourneyDTO favoriteJourneyToDTO(FavoriteJourney favoriteJourney) {
        return new FavoriteJourneyDTO(
                favoriteJourney.getJourney().getOrigin().getName(),
                favoriteJourney.getJourney().getDestination().getName(),
                favoriteJourney.getDayTimeStarts()
        );
    }
}
