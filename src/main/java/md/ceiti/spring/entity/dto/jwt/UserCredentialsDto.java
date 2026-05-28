package md.ceiti.spring.entity.dto.jwt;

import jakarta.validation.constraints.Email;


public class UserCredentialsDto {
    @Email
    private String email;
    private String password;

    public UserCredentialsDto() {
    }

    public UserCredentialsDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
