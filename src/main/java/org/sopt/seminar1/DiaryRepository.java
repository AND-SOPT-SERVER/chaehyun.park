package org.sopt.seminar1;


import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


// 저장소
public class DiaryRepository {
    private final Map<Long, String> storage = new ConcurrentHashMap<>();
    private final Map<Long, String> deletedStorage = new ConcurrentHashMap<>();
    private final Map<LocalDate, Integer> dailyPatchCount = new ConcurrentHashMap<>();
    private final AtomicLong numbering = new AtomicLong();
    private final AtomicInteger counting = new AtomicInteger();
    private final String filePath = "C:\\Sopt_Server_Seminar\\assignment\\src\\main\\java\\org\\sopt\\seminar1\\resources\\diaries.txt";


    boolean save(final Diary diary){
        if (diary.getBody().length() <= 30) {
            final long id = numbering.addAndGet(1);
            storage.put(id, diary.getBody());
            return true;
        }else {
            return false;
        }
    }

    void delete(final long id){
        String removedBody = storage.remove(id);
        deletedStorage.put(id, removedBody);
    }

    boolean patch(final Diary diary) {
        final long id = diary.getId();
        LocalDate today = LocalDate.now();
        int todayPatched = dailyPatchCount.getOrDefault(today, 0);

        if(todayPatched<2){
            storage.put(id,diary.getBody());
            dailyPatchCount.put(today, todayPatched + 1);
            return true;
        } else {
            return false;
        }

    }

    void restore(final long id){
        String body = deletedStorage.remove(id);
        storage.put(id,body);
    }

    List<Diary> findAll(){
        // 1. diary를 담을 구조
        final List<Diary> diaryList =new ArrayList<>();

        // 2. 저장한 값을 불러오는 반복구조
        for(long index = 1;index<=numbering.longValue();index++){
            final String body =  storage.get(index);

            // 2-1. 불러온 값을 구성한 자료구조로 이관
            if (body != null) { // 2-2. 삭제되어 null 값인 경우 불러오지 않음
                diaryList.add(new Diary(index, body));
            }
        }
        // 3. 불러온 자료구조를 응답
        return diaryList;
    }

    void saveToFile(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<Long, String> entry : storage.entrySet()) {
                writer.write(entry.getKey() + "|" + entry.getValue());
                writer.newLine();  // 줄바꿈
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 2) {  // id와 body로 구성된 경우만 처리
                    long id = Long.parseLong(parts[0]);
                    String body = parts[1];
                    storage.put(id, body);
                    numbering.set(id);  // 현재 id 값을 설정
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}