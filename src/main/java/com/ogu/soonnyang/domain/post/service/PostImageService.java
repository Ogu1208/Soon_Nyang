package com.ogu.soonnyang.domain.post.service;

import com.ogu.soonnyang.domain.post.entity.PostImage;
import com.ogu.soonnyang.domain.post.repository.PostImageRepository;
import com.ogu.soonnyang.util.S3Handler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostImageService {

    private final S3Handler s3Handler;
    private final PostImageRepository postImageRepository;

    @Transactional
    public void deletePostImage(Long id) {
        Optional<PostImage> postImage = postImageRepository.findById(id);
        s3Handler.deleteFile(postImage.get().getImageUrl());
        postImageRepository.deleteById(id);
    }
}