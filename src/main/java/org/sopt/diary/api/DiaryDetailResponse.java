package org.sopt.diary.api;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DiaryDetailResponse {
    private long id;
    private String title;
    private String content;
    private String dateTime;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static String formatTimestamp(LocalDateTime simpleDateTime) {
        return simpleDateTime.format(FORMATTER);
    }

    public DiaryDetailResponse(long id, String title, String content, LocalDateTime dateTime){
        this.id=id;
        this.title=title;
        this.content=content;
        this.dateTime=formatTimestamp(dateTime);
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent(){
        return content;
    }

    public String getDateTime(){
        return dateTime;
    }
}