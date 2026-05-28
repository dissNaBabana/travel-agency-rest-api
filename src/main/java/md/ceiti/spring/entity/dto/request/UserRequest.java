package md.ceiti.spring.entity.dto.request;

import jakarta.validation.constraints.Email;
import md.ceiti.spring.entity.User;
import md.ceiti.spring.entity.UserRole;

public class UserRequest {
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String phone;
    private String password;
    private UserRole role;

    public UserRequest() {
    }

    public UserRequest(String firstName, String lastName, String email, String phone, String password, UserRole role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }

    public UserRequest(String firstName, String lastName, String email, String phone, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
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

    public User toEntity(){
        return new User(
                firstName,
                lastName,
                email,
                phone,
                password,
                role != null ? role : UserRole.CLIENT);
    }

    public User toEntity(Integer id){
        return new User(
                id,
                firstName,
                lastName,
                email,
                phone,
                password,
                role);
    }
}
