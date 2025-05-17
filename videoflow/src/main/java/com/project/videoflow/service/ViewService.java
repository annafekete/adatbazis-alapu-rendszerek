package com.project.videoflow.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ViewService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void rogzitNezest(String email, Long videoId) {
        entityManager
                .createNativeQuery("BEGIN add_view_and_increment(:email, :videoid); END;")
                .setParameter("email", email)
                .setParameter("videoid", videoId)
                .executeUpdate();
    }
}
