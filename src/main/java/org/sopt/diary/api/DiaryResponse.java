package org.sopt.diary.api;

// 클라이언트 -> 서버 요청 : @RequestBody
// 서버 -> 클라이언트 응답 : @ResponseBody
// 여기서는 목록 list 사용자에게 보여줄 때만 이 파일 사용
// 그래서 Content 변수 미포함
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
