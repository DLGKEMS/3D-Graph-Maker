package com.Create_a_graph.easygraphing.controller;

import com.Create_a_graph.easygraphing.Entity.KeyValueEntity;
import com.Create_a_graph.easygraphing.repository.KeyValueEntityRepository;
import com.Create_a_graph.easygraphing.repository.MemoryJson;
import com.Create_a_graph.easygraphing.service.CsvUtils;
import com.Create_a_graph.easygraphing.service.ResetIncrement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class UploadController {

    @GetMapping("/sqltest")
    public String UploadController(){
        return "sqltest";
    }
    @Autowired
    private ResetIncrement resetIncrement;
    @Autowired
    private KeyValueEntityRepository keyValueRepository;


    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            keyValueRepository.deleteAll();
            resetIncrement.resetAutoIncrement("key_value_data");
            resetIncrement.resetAutoIncrement("key_value_entity");
            List<KeyValueEntity> entities = CsvUtils.readFromCsv(file);
            keyValueRepository.saveAll(entities);
            return "redirect:/frontTest";
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/error";
        }
    }
    @GetMapping("frontTest")
    public String showData(Model model){
        MemoryJson memoryJson = new MemoryJson();
        Map<String, String> columns = memoryJson.getColum();
        model.addAttribute("columData", columns);
        return "frontTest";
    }

//    public static class CsvUtils {
//        public static List<KeyValueEntity> readFromCsv(MultipartFile file) throws IOException {
//            try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
//                String[] header = csvReader.readNext(); // 첫 번째 라인을 헤더로 사용
//                String[] nextLine;
//                List<KeyValueEntity> entities = new ArrayList<>();
//
//                while ((nextLine = csvReader.readNext()) != null) {
//                    // nextLine 배열을 사용하여 KeyValueEntity를 생성
//                    KeyValueEntity entity = createEntity(header, nextLine);
//                    entities.add(entity);
//                }
//
//                return entities;
//            } catch (CsvValidationException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        private static KeyValueEntity createEntity(String[] header, String[] values) {
//            KeyValueEntity entity = new KeyValueEntity();
//
//            // 각 열의 값을 KeyValueEntity에 설정
//            for (int i = 0; i < Math.min(header.length, values.length); i++) {
//                entity.addColumn(header[i], values[i]);
//            }
//
//            return entity;
//        }
//    }
}

//    List<KeyValueEntity> entities = new ArrayList<>();
//            try (CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream))) {
//                    String[] header = csvReader.readNext(); // 첫 번째 라인을 헤더로 사용
//                    String[] nextLine;
//                    while ((nextLine = csvReader.readNext()) != null) {
//                    KeyValueEntity entity = new KeyValueEntity();
//                    for (int i = 0; i < header.length; i++) {
//        // 동적으로 열 생성
//        String columnName = header[i];
//        String columnValue = nextLine[i];
//        entity.addColumn(columnName, columnValue);
//        }
//        entities.add(entity);
//        }
//        } catch (CsvValidationException e) {
//        throw new RuntimeException(e);
//        }
//        return entities;