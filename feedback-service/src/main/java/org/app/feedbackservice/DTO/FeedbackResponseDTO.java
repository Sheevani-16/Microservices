package org.app.feedbackservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class FeedbackResponseDTO {
    private Long id;
    private Long userId;
    private String comment;
    private int rating;
}

