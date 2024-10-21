package org.sopt.diary.repository;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class DiaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    // @Column 어노테이션은 객체 필드와 DB 테이블 컬럼을 맵핑
    @Column
    public String title;

    @Column
    public String content;

    @Column
    public LocalDateTime dateTime;

    public DiaryEntity(){
    }

    public DiaryEntity(String title, String content){
        this.title = title;
        this.content = content;
    }

    //사용자가 직접 날짜를 입력할 필요 없이, 저장 시점의 현재 시간을 기록
    @PrePersist
    protected void onCreate() {
        this.dateTime = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }


    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }

    public LocalDateTime getDateTime(){
        return dateTime;
    }
}
