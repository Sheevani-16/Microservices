package org.app.feedbackservice.DTO;
import lombok.Data;
@Data
public class FeedbackRequestDTO {
    private Long userId;
    private String comment;
    private int rating;

    // getters and setters
}
