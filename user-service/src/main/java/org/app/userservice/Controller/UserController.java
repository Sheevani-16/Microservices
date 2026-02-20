package org.app.userservice.Controller;

import org.app.userservice.DTO.UserRequestDTO;
import org.app.userservice.DTO.UserResponseDTO;
import org.app.userservice.Service.UserService;
import org.app.userservice.Service.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping
    public UserResponseDTO createUser(@RequestBody UserRequestDTO request) {
        return userServiceImpl.createUser(request);
    }

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userServiceImpl.getAllUsers();
    }

    @GetMapping("/feedback/userID/{userID}")
    public UserResponseDTO getByUserID(@PathVariable Long userID) {
        return userServiceImpl.getAllFeedbackByUserID(userID);
    }
}
