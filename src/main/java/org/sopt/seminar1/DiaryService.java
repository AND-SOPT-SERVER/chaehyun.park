package org.sopt.seminar1;


import org.sopt.seminar1.DiaryRepository;

import java.util.List;

// 처리 로직
public class DiaryService {
    private final DiaryRepository diaryRepository = new DiaryRepository();

    boolean writeDiary(final String body) {
        final Diary diary = new Diary(null,body);
        return diaryRepository.save(diary);
    }

    List<Diary> getDiaryList(){
        return diaryRepository.findAll();
    }

    void deleteDiary(final long id) {
        diaryRepository.delete(id);
    }

    boolean patchDiary(final long id, final String body){
        final Diary diary = new Diary(id,body);
        return diaryRepository.patch(diary);
    }

    void restoreDiary(final long id){
        diaryRepository.restore(id);
    }

    void saveToFile(){
        diaryRepository.saveToFile();
    }

    void loadFromFile(){
        diaryRepository.loadFromFile();
    }
}