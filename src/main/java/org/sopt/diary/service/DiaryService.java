package org.sopt.diary.service;

import org.sopt.diary.api.DiaryDetailResponse;
import org.sopt.diary.repository.DiaryEntity;
import org.sopt.diary.repository.DiaryRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DiaryService {
    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    // 반환 타입이 없기에 -> void
    public void createDiary(String title, String content) {
        if (content.length() > 30) {
            throw new IllegalArgumentException("내용은 30자를 넘길 수 없습니다.");
        }
        diaryRepository.save(new DiaryEntity(title, content));
    }

    public void updateDiary(long id, String content) {
        DiaryEntity diaryEntity = diaryRepository.findById(id);
        if (content.length() > 30) {
            throw new IllegalArgumentException("내용은 30자를 넘길 수 없습니다.");
        }
        diaryEntity.setContent(content);
        diaryRepository.save(diaryEntity);
    }

    public List<Diary> getList() {
        // (1) repository로 부터 DiaryEntity 를 가져옴
        final List<DiaryEntity> diaryEntityList = diaryRepository.findTop10ByOrderByIdDesc();

        // (2) DiaryEntity 를 Diary 로 변환해주는 작업
        final List<Diary> diaryList = new ArrayList<>();

        for(DiaryEntity diaryEntity : diaryEntityList){
            diaryList.add(
                new Diary(diaryEntity.getId(), diaryEntity.getTitle())
            );
        }

        return diaryList;
    }

    public DiaryDetailResponse getDiaryDetail(long id){
        DiaryEntity diaryEntity = diaryRepository.findById(id);

        return new DiaryDetailResponse(diaryEntity.getId(), diaryEntity.getTitle(), diaryEntity.getContent(), diaryEntity.getDateTime());
    }
}
