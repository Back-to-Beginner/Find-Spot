package com.backend.domain.group.service;

import com.backend.domain.group.domain.entity.Group;
import com.backend.domain.group.domain.repository.GroupRepository;
import com.backend.domain.group.dto.GroupRequest;
import com.backend.domain.group.dto.GroupResponse;
import com.backend.domain.user.domain.entity.User;
import com.backend.domain.user.service.UserService;
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
public class GroupService implements
        CrudAble<GroupRequest, GroupResponse>,
        GetEntityAble<Group>,
        FindEntityAble<Group> {

    private final GroupRepository repository;
    private final UserService userService;

    @Override
    public GroupResponse save(GroupRequest groupRequest) {
        Group group = repository.save(groupRequest.toEntity());
        return GroupResponse.of(group);
    }

    @Override
    public List<GroupResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(GroupResponse::of)
                .collect(Collectors.toList());
    }

    @Override
    public GroupResponse findById(long id) {
        Group group = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND, "Group Not Found"));
        return GroupResponse.of(group);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public GroupResponse update(
            long id,
            GroupRequest groupRequest
    ) {
        Group newGroup = groupRequest.toEntity();
        return GroupResponse.of(findEntity(id).update(newGroup));
    }

    @Override
    public Group findEntity(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND, "Group Not Found")
        );
    }

    @Override
    public Group getEntity(Long id) {
        return repository.getById(id);
    }

    public GroupResponse addUser(
            Long id, Long userId
    ) {
        User user = userService.findEntity(userId);
        Group group = findEntity(id).addUser(user);
        return GroupResponse.of(group);
    }
    public GroupResponse deleteUser(
            Long id, Long userId
    ) {
        User user = userService.findEntity(userId);
        Group group = findEntity(id).deleteUser(user);
        return GroupResponse.of(group);
    }
}