package com.myshop.user.infrastructure;

import com.myshop.user.domain.User;
import com.myshop.user.domain.UserFacade;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userFacade.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id){
        return userFacade.getUser(id);
    }

    @PostMapping("/users")
    public User addUser(User user){
        return userFacade.addUser(user);
    }

    @PutMapping("/users")
    public User updateUser(Long id, User user){
        return userFacade.updateUser(id, user);
    }

    @DeleteMapping
    public void deleteUser(Long id){
        userFacade.deleteUser(id);
    }
}
