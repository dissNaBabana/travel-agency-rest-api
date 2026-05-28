package md.ceiti.spring.service;
import md.ceiti.spring.entity.User;
import md.ceiti.spring.entity.UserRole;
import md.ceiti.spring.entity.dto.request.UserRequest;
import md.ceiti.spring.entity.dto.user.UserContainerDto;
import md.ceiti.spring.entity.dto.user.UserDto;
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

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserContainerDto findAll(){
        List<UserDto> users = userRepository.findByRole(UserRole.CLIENT).stream()
                .map(User::toDto)
                .collect(Collectors.toList());
        return new UserContainerDto(users);
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


}
