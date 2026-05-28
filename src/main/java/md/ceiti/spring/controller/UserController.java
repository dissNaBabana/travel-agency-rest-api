package md.ceiti.spring.controller;

import md.ceiti.spring.entity.User;
import md.ceiti.spring.entity.dto.hotel.HotelContainerDto;
import md.ceiti.spring.entity.dto.hotel.HotelDto;
import md.ceiti.spring.entity.dto.request.HotelRequest;
import md.ceiti.spring.entity.dto.request.UserRequest;
import md.ceiti.spring.entity.dto.user.UserContainerDto;
import md.ceiti.spring.entity.dto.user.UserDto;
import md.ceiti.spring.service.HotelService;
import md.ceiti.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clients")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public UserContainerDto findAll(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable Integer id){

        return userService.findById(id);
    }

    @PostMapping()
    public UserDto save(@RequestBody UserRequest request){
        return userService.save(request);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable Integer id, @RequestBody UserRequest request){
        return userService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        userService.delete(id);
    }

}
