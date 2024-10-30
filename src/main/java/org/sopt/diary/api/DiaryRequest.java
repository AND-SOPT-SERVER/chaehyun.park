package org.sopt.diary.api;

//클라이언트 -> 서버 요청 : @RequestBody
//서버 -> 클라이언트 응답 : @ResponseBody
public class DiaryRequest {
    private long id;
    private String title;
    private String content;

    public DiaryRequest(){
    }

    public long getId() {
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getContent() {
        return content;
    }
}
