package com.backend.domain.image.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public interface ImageUploader {

    default String upload(MultipartFile multipartFile) throws IOException {
        File uploadFile = this.localUpload(multipartFile) // 파일 변환할 수 없으면 에러
                .orElseThrow(() -> new IllegalArgumentException("error: MultipartFile -> File convert fail"));
        return uploadFile.getName();
    }

    default String changeFileName() {
        return UUID.randomUUID() + ".png"; // 저장될 파일 이름 변환
    }

    // 로컬에 파일 업로드 하기
    default Optional<File> localUpload(MultipartFile file) throws IOException {
        File convertFile = new File(Path.IMAGE_SAVE_PATH.getPathName() + changeFileName());
        if (convertFile.createNewFile()) { // 바로 위에서 지정한 경로에 File이 생성됨 (경로가 잘못되었다면 생성 불가능)
            try (FileOutputStream fos = new FileOutputStream(convertFile)) { // FileOutputStream 데이터를 파일에 바이트 스트림으로 저장하기 위함
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }

}
