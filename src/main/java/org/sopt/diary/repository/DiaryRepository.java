package org.sopt.diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

// DiaryRepository는 데이터베이스와 통신하기 위한 데이터 액세스 계층
// Spring Data JPA 덕분에 복잡한 SQL 작성 없이 CRUD 작업을 수행 가능
// ex)
// save(T entity): 엔티티를 데이터베이스에 저장(새로 생성 또는 수정)
// findById(ID id): 특정 ID를 가진 엔티티 조회
// findAll(): 모든 엔티티 조회
// deleteById(ID id): 특정 ID를 가진 엔티티 삭제

@Component
public interface DiaryRepository extends JpaRepository<DiaryEntity,Long> {
    // id 필드를 기준으로 내림차순 정렬
    // Spring Data JPA에서는 메서드 이름에 조건을 명시함으로써 원하는 쿼리를 자동으로 생성
    List<DiaryEntity> findTop10ByOrderByIdDesc();

    DiaryEntity findById(long id);
}


