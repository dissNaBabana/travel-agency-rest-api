package md.ceiti.spring.service;
import md.ceiti.spring.entity.Favorite;
import md.ceiti.spring.entity.Tour;
import md.ceiti.spring.entity.User;
import md.ceiti.spring.entity.UserRole;
import md.ceiti.spring.entity.dto.request.UserRequest;
import md.ceiti.spring.entity.dto.tour.TourContainerDto;
import md.ceiti.spring.entity.dto.tour.TourDto;
import md.ceiti.spring.entity.dto.user.UserContainerDto;
import md.ceiti.spring.entity.dto.user.UserDto;
import md.ceiti.spring.entity.dto.user.UserWithoutPasswordContainerDto;
import md.ceiti.spring.entity.dto.user.UserWithoutPasswordDto;
import md.ceiti.spring.repository.FavoriteRepository;
import md.ceiti.spring.repository.TourRepository;
import md.ceiti.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FavoriteRepository favoriteRepository;
    private final TourRepository tourRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, FavoriteRepository favoriteRepository, TourRepository tourRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.favoriteRepository = favoriteRepository;
        this.tourRepository = tourRepository;
    }

    public UserWithoutPasswordContainerDto findAll(){
        List<UserWithoutPasswordDto> users = userRepository.findByRole(UserRole.CLIENT).stream()
                .map(User::toDtoWithoutPassword)
                .collect(Collectors.toList());
        return new UserWithoutPasswordContainerDto(users);
    }

    public UserDto findById(Integer id){
        return userRepository.findById(id)
                .map(User::toDto)
                .orElseThrow(() -> new IllegalArgumentException("User with id" + id + "not founded"));
    }

    public UserDto save(UserRequest request){
         User user = request.toEntity();
         user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(user).toDto();
    }

    public UserDto update(Integer id, UserRequest request){
        return userRepository.findById(id)
                .map(existingUser -> {
                    User updatedUser = request.toEntity(existingUser.getUserId());
                    return userRepository.save(updatedUser).toDto();
                })
                .orElseThrow(() -> new IllegalArgumentException("user with id" + id + "not founded"));
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public TourContainerDto findAllMyFavorite(Integer userId){
        List<TourDto> tours = favoriteRepository.findFavoriteToursByUserId(userId).stream()
                .map(Tour::toDto)
                .collect(Collectors.toList());
        return new TourContainerDto(tours);
    }

    public void markAsFavorite(User user, Integer tourId){
       Tour tour =  tourRepository.findById(tourId)
                .orElseThrow(() -> new IllegalArgumentException("Tour with id" + tourId + "not founded"));

        Favorite favorite = new Favorite(user, tour);
        favoriteRepository.save(favorite);
    }

    public void deleteFavorite(User user, Integer tourId){
        favoriteRepository.deleteByUserAndTourId(user, tourId);
    }
}
