package com.project.videoflow.config;
import com.project.videoflow.model.User;
import com.project.videoflow.repository.*;
import org.springframework.ui.Model;

import com.project.videoflow.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.List;

@ControllerAdvice
public class GlobalModelAttributeAdvice {
    private final VideoRepository videoRepository;
    private final UploadRepository uploadRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ViewRepository viewRepository;
    @Autowired
    public GlobalModelAttributeAdvice(VideoRepository videoRepository,
                                      UploadRepository uploadRepository,
                                      UserRepository userRepository,
                                      CommentRepository commentRepository,
                                      ViewRepository viewRepository) {
        this.videoRepository = videoRepository;
        this.uploadRepository = uploadRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.viewRepository = viewRepository;
    }

    @ModelAttribute
    public void addTopLikedVideos(Model model) {
        List<Video> topLikedVideos = videoRepository.findTop4MostLikedVideos();
        model.addAttribute("topLikedVideos", topLikedVideos);

        String email = uploadRepository.findTopUploaderEmail();
        User user = userRepository.findByEmail(email);
        String topUploaderName = (user != null) ? user.getFelhasznalonev() : "ismeretlen";
        model.addAttribute("topUploaderName", topUploaderName);

        String commentEmail = commentRepository.findTopCommenterEmail();
        User commentUser = userRepository.findByEmail(commentEmail);
        String topCommenterName = (commentUser != null) ? commentUser.getFelhasznalonev() : "ismeretlen";
        model.addAttribute("topCommenterName", topCommenterName);

        String topViewerName = viewRepository.findTopViewerUsername();
        model.addAttribute("topViewerName", topViewerName);
    }
}
