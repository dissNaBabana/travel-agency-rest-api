package md.ceiti.spring.controller;

import jakarta.servlet.http.HttpServletRequest;
import md.ceiti.spring.entity.dto.city.CityDto;
import md.ceiti.spring.entity.dto.jwt.JwtAuthenticatorDto;
import md.ceiti.spring.entity.dto.jwt.RefreshTokenDto;
import md.ceiti.spring.entity.dto.jwt.UserCredentialsDto;
import md.ceiti.spring.entity.dto.request.CityRequest;
import md.ceiti.spring.entity.dto.request.UserRequest;
import md.ceiti.spring.entity.dto.user.UserDto;
import md.ceiti.spring.security.jwt.TokenBlacklistService;
import md.ceiti.spring.service.AuthService;
import md.ceiti.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
   private final UserService userService;
   private final AuthService authService;
   private final TokenBlacklistService tokenBlacklistService;

   @Autowired
    public AuthController(UserService userService, AuthService authService, TokenBlacklistService tokenBlacklistService) {
        this.userService = userService;
        this.authService = authService;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    @PostMapping("/register")
    public UserDto save(@RequestBody UserRequest request){
        return userService.save(request);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<JwtAuthenticatorDto> signIn(
            @RequestBody UserCredentialsDto userCredentialsDto
    ) {
        JwtAuthenticatorDto jwtAuthenticationDto =
                authService.signIn(userCredentialsDto);

        return ResponseEntity.ok(jwtAuthenticationDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println("Logout header: " + header);
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            tokenBlacklistService.blacklist(token);
            System.out.println("Token blacklisted: " + token);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticatorDto> refresh(
            @RequestBody RefreshTokenDto refreshTokenDto
    ) {
        return ResponseEntity.ok(authService.refreshToken(refreshTokenDto));
    }
}
