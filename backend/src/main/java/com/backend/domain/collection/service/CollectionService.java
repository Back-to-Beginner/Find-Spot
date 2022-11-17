package com.backend.domain.collection.service;

import com.backend.domain.collection.domain.entity.Collection;
import com.backend.domain.collection.domain.repository.CollectionRepository;
import com.backend.domain.collection.dto.CollectionRequest;
import com.backend.domain.collection.dto.CollectionResponse;
import com.backend.domain.post.domain.entity.Post;
import com.backend.domain.post.service.PostService;
import com.backend.global.domain.CrudAble;
import com.backend.global.domain.FindEntityAble;
import com.backend.global.domain.GetEntityAble;
import com.backend.global.error.ErrorCode;
import com.backend.global.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CollectionService implements
        CrudAble<CollectionRequest, CollectionResponse>,
        GetEntityAble<Collection>,
        FindEntityAble<Collection> {

    private final CollectionRepository repository;
    private final PostService postService;

    @Override
    public CollectionResponse save(CollectionRequest collectionRequest) {
        List<Post> postList = getMissionPostList(collectionRequest);
        Collection collection = repository.save(collectionRequest.toEntity(postList));
        return CollectionResponse.of(collection);
    }

    @Override
    public List<CollectionResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(CollectionResponse::of)
                .collect(Collectors.toList());
    }

    @Override
    public CollectionResponse findById(long id) {
        Collection collection = repository.findById(id).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND, "Collection Not Found")
        );
        return CollectionResponse.of(collection);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public CollectionResponse update(long id, CollectionRequest collectionRequest) {
        List<Post> postList = getMissionPostList(collectionRequest);
        Collection newCollection = collectionRequest.toEntity(postList);
        return CollectionResponse.of(findEntity(id).update(newCollection));
    }

    private List<Post> getMissionPostList(CollectionRequest collectionRequest) {
        return collectionRequest.getPostIdList()
                .stream()
                .map(postService::findEntity)
                .filter(post -> post.getType() == 'm')
                .collect(Collectors.toList());
    }

    @Override
    public Collection findEntity(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND, "Collection Not Found")
        );
    }

    @Override
    public Collection getEntity(Long id) {
        return repository.getById(id);
    }
}