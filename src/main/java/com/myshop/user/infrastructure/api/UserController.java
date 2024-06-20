package com.myshop.user.infrastructure.api;

import com.myshop.user.domain.User;
import com.myshop.user.domain.UserFacade;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.myshop.user.infrastructure.api.UserMapper.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Long EMPTY_ID = null;
    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userFacade.getAllUsers().stream()
                .map(user -> mapUserToDto(user))
                .toList();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return mapUserToDto(userFacade.getUser(id));
    }

    @PostMapping
    public User addUser(@RequestBody UserDto userDto) {
        return userFacade.addUser(mapUserFromDto(userDto, EMPTY_ID));
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return userFacade.updateUser(id, mapUserFromDto(userDto, id));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(Long id) {
        userFacade.deleteUser(id);
    }


}
