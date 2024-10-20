package org.sopt.diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DiaryRepository extends JpaRepository<DiaryEntity,Long> {
    // id 필드를 기준으로 내림차순 정렬
    // Spring Data JPA에서는 메서드 이름에 조건을 명시함으로써 원하는 쿼리를 자동으로 생성
    List<DiaryEntity> findTop10ByOrderByIdDesc();
}
