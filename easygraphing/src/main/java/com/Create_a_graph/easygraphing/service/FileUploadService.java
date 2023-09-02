package com.Create_a_graph.easygraphing.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class FileUploadService {

    // application.properties 에 app.upload.dir을 정의하고, 없는 경우에 default 값으로 user.home (System에 종속적인)
    @Value("${app.upload.dir:${user.home}}")
    private String uploadDir;
    //private File usingfile;

//    public void fileUpload(MultipartFile multipartFile) {
//        // File.seperator 는 OS종속적이다.
//        // Spring에서 제공하는 cleanPath()를 통해서 ../ 내부 점들에 대해서 사용을 억제한다
//        Path copyOfLocation = Paths.get(uploadDir + File.separator + StringUtils.cleanPath(multipartFile.getOriginalFilename()));
//        try {
//            // inputStream을 가져와서
//            // copyOfLocation (저장위치)로 파일을 쓴다.
//            // copy의 옵션은 기존에 존재하면 REPLACE(대체한다), 오버라이딩 한다
//            Files.copy(multipartFile.getInputStream(), copyOfLocation, StandardCopyOption.REPLACE_EXISTING);
//            System.out.println(copyOfLocation);
//        } catch (IOException e) {
//            e.printStackTrace();
//           // throw new FileStorageException("Could not store file : " + multipartFile.getOriginalFilename());
//        }
//    }
    public void savefile(MultipartFile file) throws IOException {
        //csv파일 문자열 저장 < 내가 원하는 명령문은 아닌듯 바꿔야지
        String csvData = new String(file.getBytes(),"UTF-8");
        System.out.println(csvData);
        String targetdir = uploadDir + File.separator;
        Path copyOfLocation = Path.of(targetdir, file.getOriginalFilename());
        Files.copy(file.getInputStream(), copyOfLocation, StandardCopyOption.REPLACE_EXISTING);
    }
}