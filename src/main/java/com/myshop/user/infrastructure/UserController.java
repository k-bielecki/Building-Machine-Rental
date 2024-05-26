package com.myshop.user.infrastructure;

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

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Long EMPTY_ID = null;
    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userFacade.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userFacade.getUser(id);
    }

    @PostMapping
    public User addUser(@RequestBody UserDto userDto){
        return userFacade.addUser(mapUser(userDto,EMPTY_ID));
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserDto userDto){
        return userFacade.updateUser(id, mapUser(userDto, id));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(Long id){
        userFacade.deleteUser(id);
    }

    private static User mapUser(UserDto userDto, Long id){
        return User.builder()
                .userId(id)
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .nickname(userDto.getNickname())
                .password(userDto.getPassword())
                .build();
    }
}
