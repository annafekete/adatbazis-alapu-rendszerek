package com.project.videoflow.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

@Service
public class CommentService {

    private final JdbcTemplate jdbcTemplate;

    public CommentService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getCommentCountByVideoId(Long videoid) {
        return jdbcTemplate.execute((Connection con) -> {
            CallableStatement cs = con.prepareCall("{ call kommentek_szama_videohoz(?, ?) }");
            cs.setLong(1, videoid);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();
            return cs.getInt(2);
        });
    }
}
