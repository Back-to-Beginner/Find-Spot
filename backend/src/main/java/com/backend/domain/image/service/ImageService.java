package com.backend.domain.image.service;

import com.backend.domain.image.domain.ImageSlice;
import com.backend.domain.image.domain.entity.Image;
import com.backend.domain.image.domain.repository.ImageRepository;
import com.backend.domain.image.dto.ImageMapper;
import com.backend.domain.image.dto.ImageRequest;
import com.backend.domain.image.dto.ImageResponse;
import com.backend.domain.post.domain.entity.Post;
import com.backend.domain.post.service.PostService;
import com.backend.global.domain.CrudAble;
import com.backend.global.domain.FindEntityAble;
import com.backend.global.domain.GetEntityAble;
import com.backend.global.error.ErrorCode;
import com.backend.global.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class ImageService implements
        CrudAble<ImageRequest, ImageResponse>,
        GetEntityAble<Image>,
        FindEntityAble<Image> {

    private final ImageRepository repository;
    private final PostService postService;
    private final ImageUploader uploader;
    private final ImageMapper mapper;
    private final ImageAnalysis<Boolean> imageAnalysis;

    @Transactional
    public ImageResponse uploadProfile(Long postId, MultipartFile multipartFile) throws IOException {
        String path = uploader.upload(multipartFile);

        findEntityByPost(postId).forEach(Image::delete);
        repository.flush();

        Post post = postService.findEntity(postId);
        Image image = mapper.PathToEntity(post, path);

        return ImageResponse.of(repository.save(image));
    }

    @Transactional
    public String uploadImage(MultipartFile multipartFile) throws IOException {
        return uploader.upload(multipartFile);
    }

    @Override
    @Transactional
    public ImageResponse save(ImageRequest imageRequest) {
        Post post = postService.getEntity(imageRequest.getPostId());
        Image image = imageRequest.toEntity(post);
        return ImageResponse.of(repository.save(image));
    }

    @Override
    public List<ImageResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(ImageResponse::of)
                .collect(Collectors.toList());
    }

    @Override
    public ImageResponse findById(long id) {
        return ImageResponse.of(findEntity(id));
    }

    @Override
    public void deleteById(long id) {
        findEntity(id).delete();
    }

    @Override
    @Transactional
    public ImageResponse update(long id, ImageRequest imageRequest) {
        Post post = postService.findEntity(imageRequest.getPostId());
        Image newImage = imageRequest.toEntity(post);
        return ImageResponse.of(findEntity(id).update(newImage));
    }

    @Transactional
    public boolean compareImage(String challengeUrl, Long missionId) {
        String missionImagePath = findEntityByPost(missionId).get(0).getPath();

        List<Integer> slice = Arrays.stream(ImageSlice.values())
                .filter(slice1 -> slice1.getId() == missionId)
                .findAny()
                .orElse(ImageSlice.NON)
                .getSlice();

        return imageAnalysis.analyseImage(challengeUrl, missionImagePath, slice);
    }

    @Override
    public Image findEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND, "이미지를 찾을 수 없습니다."));
    }

    @Override
    public Image getEntity(Long id) {
        return repository.getById(id);
    }

    public List<ImageResponse> findByPost(Long postId) {
        return findEntityByPost(postId)
                .stream()
                .map(ImageResponse::of)
                .collect(Collectors.toList());
    }

    private List<Image> findEntityByPost(Long postId) {
        return repository.findEntityByPost(postId);
    }

}
