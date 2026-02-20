package org.app.userservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.feedbackservice.DTO.FeedbackRequestDTO;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequestDTO {
    private String name;
    private String email;
    private String password;
    private List<FeedbackRequestDTO>feedbacks=new ArrayList<>();
}
