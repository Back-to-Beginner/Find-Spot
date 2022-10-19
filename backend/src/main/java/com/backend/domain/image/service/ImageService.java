package com.backend.domain.image.service;

import com.backend.domain.image.domain.entity.Image;
import com.backend.domain.image.domain.entity.QImage;
import com.backend.domain.image.domain.repository.ImageRepository;
import com.backend.domain.image.dto.ImageMapper;
import com.backend.domain.image.dto.ImageRequest;
import com.backend.domain.image.dto.ImageResponse;
import com.backend.global.domain.CrudAble;
import com.backend.global.domain.FindEntityAble;
import com.backend.global.domain.GetEntityAble;
import com.backend.global.error.ErrorCode;
import com.backend.global.error.NotFoundException;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
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
    private final ImageUploader uploader;
    private final ImageMapper mapper;
    private final ImageAnalysis imageAnalysis;
    private final JPAQueryFactory queryFactory;
    private static final QImage qImage = QImage.image;

    @Transactional
    public ImageResponse uploadProfile(Long postId, MultipartFile multipartFile) throws IOException {
        String path = uploader.upload(multipartFile);
        findEntityByPost(postId).forEach(Image::delete);

        Image image = mapper.PathToEntity(postId, path);

        return mapper.fromEntity(repository.save(image));
    }

    @Transactional
    public String uploadImage(MultipartFile multipartFile) throws IOException {
        return uploader.upload(multipartFile);
    }

    @Override
    @Transactional
    public ImageResponse save(ImageRequest imageRequest) {
        Image image = mapper.toEntity(imageRequest);
        return mapper.fromEntity(repository.save(image));
    }

    @Override
    public List<ImageResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromEntity)
                .collect(Collectors.toList());
    }


    @Override
    public ImageResponse findById(long id) {
        return mapper.fromEntity(findEntity(id));
    }

    @Override
    public void deleteById(long id) {
        findEntity(id).delete();
    }

    @Override
    @Transactional
    public ImageResponse update(long id, ImageRequest imageRequest) {
        Image newImage = mapper.toEntity(imageRequest);
        return mapper.fromEntity(findEntity(id).update(newImage));
    }

    @Transactional
    public boolean compareImage(String challengeUrl, Long missionId) {
        String missionImagePath = findEntityByPost(missionId).get(0).getPath();

        return (boolean) imageAnalysis.analyseImage(challengeUrl, missionImagePath);
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
                .map(mapper::fromEntity)
                .collect(Collectors.toList());
    }

    public List<Image> findEntityByPost(Long postId) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qImage.post.id.eq(postId));
        booleanBuilder.and(qImage.isDeleted.eq(false));

        return queryFactory.selectFrom(qImage)
                .where(booleanBuilder)
                .orderBy(qImage.updatedAt.desc())
                .fetch();
    }
}
