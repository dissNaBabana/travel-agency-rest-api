package md.ceiti.spring.entity.dto.jwt;

public class JwtAuthenticatorDto {
    private String token;
    private String refreshToken;
    private String role;           // ← вот это важно
    private String userName;
    private Integer userId;
    private String email;

    public JwtAuthenticatorDto() {}
    public String getToken() { return token; }
    public void setToken(String v) { this.token = v; }
    public String getRefreshToken() { return refreshToken; }
    public void setRefreshToken(String v) { this.refreshToken = v; }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
