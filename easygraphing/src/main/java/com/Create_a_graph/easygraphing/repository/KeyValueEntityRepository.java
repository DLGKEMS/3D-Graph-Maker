package com.Create_a_graph.easygraphing.repository;

import com.Create_a_graph.easygraphing.Entity.KeyValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyValueEntityRepository extends JpaRepository<KeyValueEntity, Long> {

}

// 단일 키에 해당하는 값 조회
//    List<KeyValueEntity> findByKeyName(String keyName);
//
//    // 특정 값에 해당하는 키 조회
//    List<KeyValueEntity> findByValue(String value);
// 더 많은 쿼리 메서드 추가 가능
//void saveAll(List<KeyValueEntity> entities);