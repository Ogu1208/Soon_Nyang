package com.ogu.soonnyang.domain.file;

import com.ogu.soonnyang.domain.post.entity.Post;
import com.ogu.soonnyang.domain.post.entity.PostImage;
import com.ogu.soonnyang.domain.post.repository.PostImageRepository;
import com.ogu.soonnyang.util.S3Handler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FileService {

    private final PostImageRepository postImageRepository;
    private final S3Handler s3Handler;


    @Transactional
    public void uploadPostImages(List<MultipartFile> multipartFileList, Post post){
        if (multipartFileList != null) {
            for (MultipartFile file : multipartFileList) {
                String imgUrl = s3Handler.uploadPostFiles(file, post.getCat().getName());
                PostImage postImage = PostImage.builder()
                        .post(post)
                        .originalFileName(file.getOriginalFilename())
                        .imageUrl(imgUrl)
                        .build();

                postImageRepository.save(postImage);
            }
        }
    }
}
