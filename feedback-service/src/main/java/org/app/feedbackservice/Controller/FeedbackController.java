package org.app.feedbackservice.Controller;

import org.app.feedbackservice.DTO.FeedbackRequestDTO;
import org.app.feedbackservice.DTO.FeedbackResponseDTO;
import org.app.feedbackservice.Service.FeedbackService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/feedbacks")
    public class FeedbackController {

        private final FeedbackService feedbackService;

        public FeedbackController(FeedbackService feedbackService) {
            this.feedbackService = feedbackService;
        }

        @PostMapping
        public FeedbackResponseDTO createFeedback(@RequestBody FeedbackRequestDTO request) {
            return feedbackService.createFeedback(request);
        }

        @GetMapping("/user/{userId}")
        public List<FeedbackResponseDTO> getFeedbackByUser(@PathVariable Long userId) {
            return feedbackService.getFeedbackByUser(userId);
        }
    }

