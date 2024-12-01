package cat.tecnocampus.fgcstations.application;

import cat.tecnocampus.fgcstations.application.DTOs.*;
import cat.tecnocampus.fgcstations.application.exception.UserDoesNotExistsException;
import cat.tecnocampus.fgcstations.application.mapper.MapperHelper;
import cat.tecnocampus.fgcstations.domain.DayTimeStart;
import cat.tecnocampus.fgcstations.domain.FavoriteJourney;
import cat.tecnocampus.fgcstations.domain.Journey;
import cat.tecnocampus.fgcstations.domain.User;
import cat.tecnocampus.fgcstations.persistence.DayTimeStartRepository;
import cat.tecnocampus.fgcstations.persistence.FavoriteJourneyRepository;
import cat.tecnocampus.fgcstations.persistence.JourneyRepository;
import cat.tecnocampus.fgcstations.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FcgUserService {
    
    private final UserRepository userRepository;
    private final FavoriteJourneyRepository favoriteJourneyRepository;
    private final JourneyRepository journeyRepository;
    private final DayTimeStartRepository dayTimeStartRepository;
    private final FgcStationService fgcStationService;
    
    @Autowired
    public FcgUserService(UserRepository userRepository, FavoriteJourneyRepository favoriteJourneyRepository, JourneyRepository journeyRepository, DayTimeStartRepository dayTimeStartRepository, FgcStationService fgcStationService) {
        this.userRepository = userRepository;
        this.favoriteJourneyRepository = favoriteJourneyRepository;
        this.journeyRepository = journeyRepository;
        this.dayTimeStartRepository = dayTimeStartRepository;
        this.fgcStationService = fgcStationService;
    }
    
    public UserDTO getUserDTO(String username) {
        // TODO 10.0: get the user (domain) given her username.
        
        // TODO 11.0: get the user's favorite journeys
        User user = getDomainUser(username);
        
        if (user == null) {
            throw new UserDoesNotExistsException("User with username " + username + " does not exist");
        }
        
        List<FavoriteJourney> favoriteJourneys = getFavoriteJourneys(username);
        
        return new UserDTO(user.getUsername(), user.getName(), user.getSecondName(), user.getEmail(), favoriteJourneys);
    }
    
    public User getDomainUser(String username) {
        // TODO 10.1: get the user (domain) given her username. If the user does not exist, throw a UserDoesNotExistsException
        //  You can solve this exercise without leaving this file
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserDoesNotExistsException("User with username " + username + " does not exist"));
    }
    
    public UserDTOnoFJ getUserDTOWithNoFavoriteJourneys(String username) {
        // TODO 12: get the user (UserDTOnoFJ) given her username. If the user does not exist, throw a UserDoesNotExistsException
        User user = getDomainUser(username); // Usa el método del TODO 10.1
        return new UserDTOnoFJ(user.getUsername(), user.getName(), user.getSecondName(), user.getEmail());
    }
    
    public UserDTOInterface getUserDTOInterface(String username) {
        // TODO 13: get the user (UserDTOInterface) given her username. If the user does not exist, throw a UserDoesNotExistsException
        User user = getDomainUser(username);
        return new UserDTO(user.getUsername(), user.getName(), user.getSecondName(), user.getEmail(), new ArrayList<>());
    }
    
    public List<UserDTO> getUsers() {
        //TODO 14: get all users (domain). You can solve this exercise without leaving this file
        List<User> users;
        users = userRepository.findAll();
       
        return users.stream().map(MapperHelper::userToUserDTO).toList();
    }
    
    // TODO 22: This method updates a user. Make sure the user is saved without explicitly calling the save method
    public void updateUser(UserDTOnoFJ userDTO) {
        User user = getDomainUser(userDTO.getUsername());
        
        if (user != null) {
            user.setName(userDTO.getName());
            user.setSecondName(userDTO.getSecondName());
            user.setEmail(userDTO.getEmail());
        } else {
            throw new UserDoesNotExistsException("User with username " + userDTO.getUsername() + " does not exist");
        }
    }
    
    public List<UserTopFavoriteJourney> getTop3UsersWithMostFavoriteJourneys() {
        // TODO 16: get the top 3 users (UserTopFavoriteJourney) with the most favorite journeys
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserTopFavoriteJourney(
                        user.getUsername(),
                        user.getName(),
                        user.getSecondName(),
                        user.getEmail(),
                        user.getFavoriteJourneyList().size()))
                .sorted((u1, u2) -> Integer.compare(u2.getNumberOfFavoriteJourneys(), u1.getNumberOfFavoriteJourneys()))
                .limit(3)
                .toList();
    }
    
    public List<UserDTO> getUsersByNameAndSecondName(String name, String secondName) {
        // TODO 17: get the users (UserDTOInterface) given their name and second name. Try not to use any sql (jpql) query
        Optional<User> users = userRepository.findByNameAndSecondName(name, secondName);
        return users.stream()
                .map(user -> new UserDTO(user.getUsername(), user.getName(), user.getSecondName(), user.getEmail(), new ArrayList<>()))
                .toList();
    }
    
    public List<FavoriteJourney> getFavoriteJourneys(String username) {
        // TODO 11.1: get the user's favorite journeys given the User (domain object)
        User user = getDomainUser(username);
        
        return favoriteJourneyRepository.findByUser(user);
    }
    
    public List<FavoriteJourneyDTO> getFavoriteJourneysDTO(String username) {
        List<FavoriteJourney> favoriteJourneys = getFavoriteJourneys(username);
        
        return favoriteJourneys.stream()
                .map(favoriteJourney -> new FavoriteJourneyDTO(
                        favoriteJourney.getJourney().getOrigin().getName(),
                        favoriteJourney.getJourney().getDestination().getName(),
                        convertDayTimeStartsToDTO(favoriteJourney.getDayTimeStarts())
                ))
                .toList();
    }
    
    private List<DayTimeStartDTO> convertDayTimeStartsToDTO(List<DayTimeStart> dayTimeStarts) {
        // Aquí convertimos cada DayTimeStart (entidad) a su correspondiente DTO
        return dayTimeStarts.stream()
                .map(dayTimeStart -> new DayTimeStartDTO(dayTimeStart.getStartTime(), dayTimeStart.getDay()))
                .toList();
    }
    
    //Adding a favorite journey to the user. We need to save a favorite journey that didn't exist before
    public void addUserFavoriteJourney(String username, FavoriteJourneyDTO favoriteJourneyDTO) {
        // TODO 19: explicitly save the journey. You can solve this exercise without leaving this file
        // TODO 20: explicitly save the favorite journey. You can solve this exercise without leaving this file
        // TODO 21: explicitly save all the dayTimeStarts of the favorite journey. You can solve this exercise without leaving this file
        FavoriteJourney favoriteJourney = convertFavoriteJourneyDTO(username, favoriteJourneyDTO);
        journeyRepository.save(favoriteJourney.getJourney());
        favoriteJourneyRepository.save(favoriteJourney);
        dayTimeStartRepository.saveAll(favoriteJourney.getDayTimeStarts());
    }
    
    private FavoriteJourney convertFavoriteJourneyDTO(String username, FavoriteJourneyDTO favoriteJourneyDTO) {
        FavoriteJourney favoriteJourney = new FavoriteJourney();
        favoriteJourney.setUser(getDomainUser(username));
        favoriteJourney.setId(UUID.randomUUID().toString());
        Journey journey = new Journey(fgcStationService.getStation(favoriteJourneyDTO.getOrigin()), fgcStationService.getStation(favoriteJourneyDTO.getDestination()));
        favoriteJourney.setJourney(journey);
        
        return favoriteJourney;
    }
    
    public List<PopularDayOfWeek> getPopularDayOfWeek() {
        // TODO 18: get the most popular day of the week (PopularDayOfWeek) among the dayTimeStarts
        return dayTimeStartRepository.findMostPopularDays();
    }
}