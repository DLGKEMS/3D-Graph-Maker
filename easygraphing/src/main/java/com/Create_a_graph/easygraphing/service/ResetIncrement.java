package com.Create_a_graph.easygraphing.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ResetIncrement {
    private EntityManager entityManager;

    public ResetIncrement(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional //트랜잭션을 시작
    public void resetAutoIncrement(String tableName) {
        String sql = "ALTER TABLE " + tableName + " AUTO_INCREMENT = 1";
        entityManager.createNativeQuery(sql).executeUpdate();
    }
}
