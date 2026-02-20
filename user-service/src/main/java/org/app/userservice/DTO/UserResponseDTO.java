package org.app.userservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.feedbackservice.DTO.FeedbackResponseDTO;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private FeedbackResponseDTO feedbacks;
}
