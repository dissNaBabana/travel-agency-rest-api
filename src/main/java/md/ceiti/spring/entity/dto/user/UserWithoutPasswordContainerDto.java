package md.ceiti.spring.entity.dto.user;

import java.util.List;

public class UserWithoutPasswordContainerDto {
    private final List<UserWithoutPasswordDto> users;

    public UserWithoutPasswordContainerDto(List<UserWithoutPasswordDto> users) {
        this.users = users;
    }

    public List<UserWithoutPasswordDto> getUsers() {
        return users;
    }
}
