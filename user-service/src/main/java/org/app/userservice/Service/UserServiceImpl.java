package org.app.userservice.Service;

import org.app.feedbackservice.DTO.FeedbackResponseDTO;
import org.app.userservice.DTO.UserRequestDTO;
import org.app.userservice.DTO.UserResponseDTO;
import org.app.userservice.Repository.UserRepository;
import org.app.userservice.model.User;
import org.app.userservice.utils.RestTemplateUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RestTemplateUtils restTemplateUtils;
    private final RestTemplate restTemplate;

    public UserServiceImpl(UserRepository userRepository, RestTemplateUtils restTemplateUtils, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplateUtils = restTemplateUtils;
        this.restTemplate = restTemplate;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        User saved = userRepository.save(user);


        UserResponseDTO response = new UserResponseDTO();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setEmail(saved.getEmail());
        return response;
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDTO> responseList = new ArrayList<>();

        for (User user : users) {
            UserResponseDTO dto = new UserResponseDTO();
            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setEmail(user.getEmail());
            responseList.add(dto);
        }

        return responseList;
    }

    @Override
    public UserResponseDTO getAllFeedbackByUserID(long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            return null;
        }

        User user = userOptional.get();
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());

        try {
            String url = "http://feedback-service:8082/feedbacks/user/" + user.getId();
            ResponseEntity<List<FeedbackResponseDTO>> response = restTemplateUtils.callInterService(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<FeedbackResponseDTO>>() {}
            );

            List<FeedbackResponseDTO> feedbacks = response.getBody();
//            dto.setFeedbacks(feedbacks != null ? feedbacks : new ArrayList<>());

        } catch (Exception e) {
//            dto.setFeedbacks(new ArrayList<>());
        }

        return dto;
    }

    @Override
    public UserResponseDTO createfeedback(UserRequestDTO request) {


        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        User saved = userRepository.save(user);

        String url = "http://feedback-service:8082/feedbacks/" + saved.getId();
        FeedbackResponseDTO fDTO = restTemplate.postForObject(url,request.getFeedbacks(), FeedbackResponseDTO.class);


        UserResponseDTO response = new UserResponseDTO();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setEmail(saved.getEmail());
        response.setFeedbacks(fDTO);
        return response;
    }
}

//    @Override
//    public List<UserResponseDTO> getAllFeedbackByUserID(long id) {
//        List<UserResponseDTO> userResponseList = new ArrayList<>();
//
//        Optional<User> userOptional = userRepository.findById(id);
//
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//
//            // Map User to DTO
//            UserResponseDTO dto = new UserResponseDTO();
//            dto.setId(user.getId());
//            dto.setName(user.getName());
//            dto.setEmail(user.getEmail());
//
//            // Call FeedbackService
//            try {
//                String url = "http://localhost:8082/feedbacks/user/" + user.getId();
//                ResponseEntity<List<FeedbackResponseDTO>> response = restTemplateUtils.callInterService(
//                        url,
//                        HttpMethod.GET,
//                        null,
//                        new ParameterizedTypeReference<List<FeedbackResponseDTO>>() {}
//                );
//
//                List<FeedbackResponseDTO> feedbacks = response.getBody();
//                dto.setFeedbacks(feedbacks != null ? feedbacks : new ArrayList<>());
//
//            } catch (Exception e) {
//                System.out.println("Feedback service call failed for user: " + user.getId());
//                dto.setFeedbacks(new ArrayList<>()); // empty list if call fails
//            }
//
//            userResponseList.add(dto);
//        } else {
//            System.out.println("User not found with id: " + id);
//        }
//
//        return userResponseList;
//    }
