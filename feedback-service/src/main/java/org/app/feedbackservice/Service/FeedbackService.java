package org.app.feedbackservice.Service;

import org.app.feedbackservice.DTO.FeedbackRequestDTO;
import org.app.feedbackservice.DTO.FeedbackResponseDTO;

import java.util.List;

public interface FeedbackService {
    FeedbackResponseDTO createFeedback(FeedbackRequestDTO request);
    List<FeedbackResponseDTO> getFeedbackByUser(Long userId);
    FeedbackResponseDTO createFeedbackUID(Long id, FeedbackRequestDTO request);
}
