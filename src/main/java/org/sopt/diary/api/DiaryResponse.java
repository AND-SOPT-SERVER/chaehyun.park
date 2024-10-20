package org.sopt.diary.api;

//클라이언트 -> 서버 요청 : @RequestBody
//서버 -> 클라이언트 응답 : @ResponseBody
public class DiaryResponse {
    private long id;
    private String title;

    public DiaryResponse(long id, String title){
        this.id=id;
        this.title=title;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
