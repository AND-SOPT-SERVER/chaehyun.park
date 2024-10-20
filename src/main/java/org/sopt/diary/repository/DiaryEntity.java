package org.sopt.diary.repository;

import jakarta.persistence.*;

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

    public DiaryEntity(){
    }

    public DiaryEntity(String title, String content){
        this.title = title;
        this.content = content;
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
}
