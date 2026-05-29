package md.ceiti.spring.controller;

import md.ceiti.spring.entity.dto.city.CityDto;
import md.ceiti.spring.entity.dto.jwt.JwtAuthenticatorDto;
import md.ceiti.spring.entity.dto.jwt.UserCredentialsDto;
import md.ceiti.spring.entity.dto.request.CityRequest;
import md.ceiti.spring.entity.dto.request.UserRequest;
import md.ceiti.spring.entity.dto.user.UserDto;
import md.ceiti.spring.service.AuthService;
import md.ceiti.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

   @Autowired
    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

//    POST   /auth/logout

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
}
