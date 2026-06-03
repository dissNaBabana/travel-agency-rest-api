package md.ceiti.spring.controller.forPrivate.admin;
import md.ceiti.spring.entity.dto.user.UpdateRoleDto;
import md.ceiti.spring.entity.dto.user.UserDto;
import md.ceiti.spring.entity.dto.user.UserWithoutPasswordContainerDto;
import md.ceiti.spring.entity.dto.user.UserWithoutPasswordDto;
import md.ceiti.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("hasRole('SUPER_ADMIN')")
@RestController
@RequestMapping("/api/v1/admins")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public UserWithoutPasswordContainerDto findAll(){
        return userService.findAllAdmins();
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @PatchMapping("/{id}/role")
    public UserWithoutPasswordDto updateRoles(@PathVariable Integer id, @RequestBody UpdateRoleDto updateRoleDto) {
        return userService.updateRole(id, updateRoleDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }

}
