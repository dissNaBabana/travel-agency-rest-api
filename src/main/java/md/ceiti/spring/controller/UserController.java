package md.ceiti.spring.controller;

import md.ceiti.spring.entity.User;
import md.ceiti.spring.entity.dto.hotel.HotelContainerDto;
import md.ceiti.spring.entity.dto.hotel.HotelDto;
import md.ceiti.spring.entity.dto.request.HotelRequest;
import md.ceiti.spring.entity.dto.request.UserRequest;
import md.ceiti.spring.entity.dto.user.UserContainerDto;
import md.ceiti.spring.entity.dto.user.UserDto;
import md.ceiti.spring.security.CustomUserDetails;
import md.ceiti.spring.service.HotelService;
import md.ceiti.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clients")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public UserDto findById(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return userService.findById(userDetails.getUser().getUserId());
    }

    @PutMapping("/me")
    public UserDto update(@AuthenticationPrincipal CustomUserDetails userDetails,
                            @RequestBody UserRequest request) {
        return userService.update(userDetails.getUser().getUserId(), request);
    }

    @DeleteMapping("/me")
    public void delete(@AuthenticationPrincipal CustomUserDetails userDetails) {
        userService.delete(userDetails.getUser().getUserId());
    }

}
