package md.ceiti.spring.entity.dto.user;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import md.ceiti.spring.entity.UserRole;

public class UserDto {
    private Integer userId;
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String phone;
    private String password;
    private UserRole role;

    public UserDto() {
    }

    public UserDto(Integer userId, String firstName, String lastName, String email, String phone, String password, UserRole role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }
}
