package org.sopt.diary.api;

import org.sopt.diary.service.Diary;
import org.sopt.diary.service.DiaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    // ResponseEntity<String> 여기에서 <요기> 안에 들어가는 값에 대한 궁금증 발생!
    // 여러 개의 필드(예: ID, 제목, 내용)를 함께 전달할 필요가 있다면 DiaryResponse와 같은 객체를 사용해야 함
    @PostMapping("/diary")
    ResponseEntity<String> post(@RequestBody DiaryRequest request) {
        try {
            diaryService.createDiary(request.getTitle(), request.getContent());
            return ResponseEntity.ok("일기가 작성되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/diaries")
    ResponseEntity<DiaryListResponse> getDiary() {
        // Service로부터 가져온 DiaryList
        List<Diary> diaryList = diaryService.getList();

        // Client 와 협의한 interface 로 변화
        List<DiaryResponse> diaryResponseList = new ArrayList<>();
        for(Diary diary : diaryList) {
            diaryResponseList.add(new DiaryResponse(diary.getId(), diary.getTitle()));
        }

        return ResponseEntity.ok(new DiaryListResponse(diaryResponseList));
    }

    // @PathVariable long id는 요청 URL에서 {id}를 추출하여 id 변수에 할당
    @GetMapping("/diary/{id}")
    ResponseEntity<DiaryDetailResponse> getDiaryDetail(@PathVariable long id){
        DiaryDetailResponse diaryResponseDetail = diaryService.getDiaryDetail(id);
        return ResponseEntity.ok(diaryResponseDetail);
    }
}
