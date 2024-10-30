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

    // Getter (getContent)
    // 역할: 객체의 필드 값을 가져와 보여줌
    public String getContent(){
        return content;
    }
    // Setter (setContent)
    // 역할: 객체의 필드 값을 변경하는 메서드, 외부에서 객체의 상태를 변경하고 싶을 때 사용
    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDateTime(){
        return dateTime;
    }
}
