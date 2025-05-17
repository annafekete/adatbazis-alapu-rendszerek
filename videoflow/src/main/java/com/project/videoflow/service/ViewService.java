package com.project.videoflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ViewService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ViewService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void rogzitNezest(String email, Long videoid) {
        jdbcTemplate.update("CALL video_nezes_rogzit(?, ?)", email, videoid);
    }
}
