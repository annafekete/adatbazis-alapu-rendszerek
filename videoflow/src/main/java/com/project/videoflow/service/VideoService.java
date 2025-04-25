/*

package com.project.videoflow.service;

import com.project.videoflow.model.Video;
import com.project.videoflow.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    public Video saveVideo(Video video) {
        return videoRepository.save(video);
    }


    public Optional<Video> getVideoById(Long id) {
        return videoRepository.findById(id);
    }

    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    public List<Video> getVideosByCategory(String category) {
        return videoRepository.findByCategory(category);
    }

    public List<Video> getVideosByUser(String username) {
        return videoRepository.findByUsername(username);
    }

    public Page<Video> searchVideos(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return videoRepository.search(keyword, pageable);
    }

    public List<Video> getTopVideos(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return videoRepository.findTopVideos(pageable);
    }

    public List<Video> getRecentVideos(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return videoRepository.findRecentVideos(pageable);
    }

    public void incrementViews(Long videoId) {
        Optional<Video> videoOpt = videoRepository.findById(videoId);
        if (videoOpt.isPresent()) {
            Video video = videoOpt.get();
            video.incrementViews();
            videoRepository.save(video);
        }
    }

    public void likeVideo(Long videoId) {
        Optional<Video> videoOpt = videoRepository.findById(videoId);
        if (videoOpt.isPresent()) {
            Video video = videoOpt.get();
            video.incrementLikes();
            videoRepository.save(video);
        }
    }

    public void deleteVideo(Long id) {
        videoRepository.deleteById(id);
    }
}
*/
