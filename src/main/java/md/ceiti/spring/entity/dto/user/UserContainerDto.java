package md.ceiti.spring.entity.dto.user;

import md.ceiti.spring.entity.dto.tour.TourDto;

import java.util.List;

public class UserContainerDto {
    private final List<UserDto> users;

    public UserContainerDto(List<UserDto> users) {
        this.users = users;
    }

    public List<UserDto> getUsers() {
        return users;
    }
}
