package org.app.feedbackservice.Service;

import org.app.feedbackservice.DTO.FeedbackRequestDTO;
import org.app.feedbackservice.DTO.FeedbackResponseDTO;
import org.app.feedbackservice.Repository.FeedbackRepository;
import org.app.feedbackservice.model.Feedback;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public FeedbackResponseDTO createFeedback(FeedbackRequestDTO request) {
        Feedback feedback = new Feedback();
        feedback.setUserId(request.getUserId());
        feedback.setComment(request.getComment());
        feedback.setRating(request.getRating());

        Feedback saved = feedbackRepository.save(feedback);

        FeedbackResponseDTO response = new FeedbackResponseDTO();
        response.setId(saved.getId());
        response.setUserId(saved.getUserId());
        response.setComment(saved.getComment());
        response.setRating(saved.getRating());

        return response;
    }

    @Override
    public List<FeedbackResponseDTO> getFeedbackByUser(Long userId) {
        List<Feedback> feedbacks = feedbackRepository.findByUserId(userId);
        List<FeedbackResponseDTO> responseList = new ArrayList<>();

        for (Feedback fb : feedbacks) {
            FeedbackResponseDTO dto = new FeedbackResponseDTO();
            dto.setId(fb.getId());
            dto.setUserId(fb.getUserId());
            dto.setComment(fb.getComment());
            dto.setRating(fb.getRating());
            responseList.add(dto);
        }

        return responseList;
    }

    @Override
    public FeedbackResponseDTO createFeedbackUID(Long id, FeedbackRequestDTO request) {
        Feedback feedback = new Feedback();
        feedback.setUserId(id);
        feedback.setComment(request.getComment());
        feedback.setRating(request.getRating());

        Feedback saved = feedbackRepository.save(feedback);

        FeedbackResponseDTO response = new FeedbackResponseDTO();
        response.setId(saved.getId());
        response.setUserId(saved.getUserId());
        response.setComment(saved.getComment());
        response.setRating(saved.getRating());

        return response;
    }
}
