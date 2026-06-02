package md.ceiti.spring.service;

import md.ceiti.spring.entity.User;
import md.ceiti.spring.entity.dto.jwt.JwtAuthenticatorDto;
import md.ceiti.spring.entity.dto.jwt.RefreshTokenDto;
import md.ceiti.spring.entity.dto.jwt.UserCredentialsDto;
import md.ceiti.spring.entity.dto.request.UserRequest;
import md.ceiti.spring.entity.dto.user.UserDto;
import md.ceiti.spring.repository.UserRepository;
import md.ceiti.spring.security.jwt.JwtService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       JwtService jwtService,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public JwtAuthenticatorDto signIn(UserCredentialsDto dto) {

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() ->
                        new BadCredentialsException("Invalid email or password")
                );

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        return jwtService.generateAuthToken(user.getEmail());
    }

    public UserDto save(UserRequest request) {
        User user = request.toEntity();

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user).toDto();
    }

    public JwtAuthenticatorDto refreshToken(RefreshTokenDto dto) {
        String token = dto.getRefreshToken();

        if (token == null) {
            throw new BadCredentialsException("Refresh token is missing");
        }

        String email;
        try {
            email = jwtService.getEmailFromExpiredToken(token);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid refresh token");
        }

        userRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("User not found"));

        if (!jwtService.validateRefreshToken(token)) {
            throw new BadCredentialsException("Refresh token expired, please sign in again");
        }

        return jwtService.refreshBaseToken(email, token);
    }
}