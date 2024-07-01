package com.buildingMachineRental.user.infrastructure.api;

import com.buildingMachineRental.user.domain.InvalidUserException;
import com.buildingMachineRental.user.domain.User;
import com.buildingMachineRental.user.domain.UserFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

import static com.buildingMachineRental.user.infrastructure.api.UserMapper.mapUserFromDto;
import static com.buildingMachineRental.user.infrastructure.api.UserMapper.mapUserToDto;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Long EMPTY_ID = null;
    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> response = userFacade.getAllUsers().stream()
                .map(UserMapper::mapUserToDto)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        UserDto response = mapUserToDto(userFacade.getUser(id));

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto) {
        try {
            User user = userFacade.addUser(mapUserFromDto(userDto, EMPTY_ID));
            URI uri = URI.create("/users/" + user.getUserId());

            return ResponseEntity.created(uri).build();

        } catch (InvalidUserException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        try {
            User user = userFacade.updateUser(id, mapUserFromDto(userDto, id));
            URI uri = URI.create("/users/" + user.getUserId());

            return ResponseEntity.created(uri).build();
        } catch (InvalidUserException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(Long id) {
        userFacade.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}
