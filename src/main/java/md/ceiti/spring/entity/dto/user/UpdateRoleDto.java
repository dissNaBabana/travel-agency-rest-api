package md.ceiti.spring.entity.dto.user;


import md.ceiti.spring.entity.UserRole;

public class UpdateRoleDto {
    private UserRole userRole;

    public UpdateRoleDto() {
    }

    public UpdateRoleDto(UserRole userRole) {
        this.userRole = userRole;
    }


    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

}