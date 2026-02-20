package org.app.feedbackservice.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackRequestDTO {
    private Long userId;
    private String comment;
    private int rating;
}
