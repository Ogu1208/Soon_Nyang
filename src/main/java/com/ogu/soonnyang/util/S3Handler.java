package com.ogu.soonnyang.util;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class S3Handler {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

//    public String uploadPostFiles(MultipartFile uploadFile, String catName) throws IOException {
//        String ext = getExt(uploadFile);
//        String fileName = UUID.randomUUID() + "." + ext;
//
//        try {
//            if (isImageFile(uploadFile)) {
//                return putS3(uploadFile, fileName, "postImages/" + catName);
//            } else {
//                return putS3(uploadFile, fileName, "postFiles/" + catName);
//            }
//        } catch (IOException e) {
//            throw new IOException("Failed to upload file to server with method : [uploadPostImage(MultipartFile uploadFile, String catName)]", e);
//        }
//    }

    public String uploadFile(MultipartFile uploadFile, String catName, String directory){
        String ext = getExt(uploadFile);
        String fileName = UUID.randomUUID() + "." + ext;

        try {
            return putS3(uploadFile, fileName, directory + "/" + catName + "/");
        } catch (IOException e) {
            log.error("Failed to upload file to server with method : [uploadFile(MultipartFile uploadFile, String catName, String directory)]", e);
            return null;
        }
    }

    public String uploadPostFiles(MultipartFile uploadFile, String catName){
        if (isImageFile(uploadFile)) {
            return uploadFile(uploadFile, catName, "postImages");
        } else {
            return uploadFile(uploadFile, catName, "postFiles");
        }
    }

    public String uploadCatProfile(MultipartFile uploadFile, String catName){
        return uploadFile(uploadFile, catName, "catProfiles");
    }


    // S3로 업로드
    private String putS3(MultipartFile uploadFile, String fileName, String dirName) throws IOException {
        String result = null;
        String fileKey = dirName + fileName;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(uploadFile.getContentType());
        metadata.setContentLength(uploadFile.getSize());

        amazonS3Client.putObject(new PutObjectRequest(bucket, fileKey, uploadFile.getInputStream(), metadata));

        // Amazon S3 클라이언트를 사용하여 업로드된 파일의 URL을 가져오기
        URL url = amazonS3Client.getUrl(bucket, fileKey);
        if (url != null) {
            result = url.toString();
        }

        return result;
    }

    //파일 삭제
    public void deleteFile(String fileUrl) {
        // "amazonaws.com/" 이후의 부분 추출
        String searchString = "amazonaws.com/";
        int findKeyIndex = fileUrl.lastIndexOf(searchString) + searchString.length();

        String key = fileUrl.substring(findKeyIndex);

        amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, key));

        System.out.printf("[%s] deletion complete%n", key);

    }

    public String getExt(MultipartFile imgFile) {
        //사진 확장자
        int pos = imgFile.getOriginalFilename().lastIndexOf(".");
        return imgFile.getOriginalFilename().substring(pos + 1);
    }

    public boolean isImageFile(MultipartFile file) {
        String ext = getExt(file);

        return file.getContentType() != null && file.getContentType().startsWith("image");
    }
}
