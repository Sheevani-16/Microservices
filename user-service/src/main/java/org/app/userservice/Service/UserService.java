package org.app.userservice.Service;

import org.app.userservice.DTO.UserRequestDTO;
import org.app.userservice.DTO.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO request);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getAllFeedbackByUserID(long id);
    UserResponseDTO createfeedback(UserRequestDTO request);
}

