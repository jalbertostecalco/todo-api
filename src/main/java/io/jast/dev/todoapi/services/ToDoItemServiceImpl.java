package io.jast.dev.todoapi.services;

import io.jast.dev.todoapi.dtos.ToDoItemRequestDTO;
import io.jast.dev.todoapi.dtos.ToDoItemResponseDTO;
import io.jast.dev.todoapi.entities.ToDoItemEntity;
import io.jast.dev.todoapi.enums.InternalErrorCode;
import io.jast.dev.todoapi.exceptions.NotFoundException;
import io.jast.dev.todoapi.mappers.ToDoItemMapper;
import io.jast.dev.todoapi.repositories.ToDoItemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ToDoItemServiceImpl {

    private ToDoItemRepository toDoItemRepository;

    public ToDoItemServiceImpl(ToDoItemRepository toDoItemRepository){
        this.toDoItemRepository = toDoItemRepository;
    }

    public ToDoItemResponseDTO getById(Integer id) {
        var result = toDoItemRepository.findById(id);
        if (result.isEmpty()) throw new NotFoundException(InternalErrorCode.TODO_NOT_FOUND, "The ToDo with ID %s not found".formatted(id.toString()));

        return ToDoItemMapper.toDto(result.get());
    }

    public List<ToDoItemResponseDTO> getAll() {
        return this.toDoItemRepository.getAll()
                .stream()
                .map(ToDoItemMapper::toDto)
                .toList();
    }
    public Integer add(ToDoItemRequestDTO toDoItem) {
        ToDoItemEntity entity = ToDoItemMapper.toEntity(toDoItem);
        var savedEntity = toDoItemRepository.save(entity);
        return savedEntity.getId();
    }
    public void update(Integer id, ToDoItemRequestDTO toDoItem) {
        var result = toDoItemRepository.findById(id);
        if (result.isEmpty()) throw new NotFoundException(InternalErrorCode.TODO_NOT_FOUND, "The ToDo with ID %s not found".formatted(id.toString()));

        ToDoItemEntity entity = result.get();
        entity.setTitle(toDoItem.getTitle());
        entity.setDescription(toDoItem.getDescription());
        entity.setUpdatedAt(LocalDateTime.now());
        toDoItemRepository.save(entity);
    }

    public void delete(Integer id) {
        var result = toDoItemRepository.findById(id);
        if (result.isEmpty()) throw new NotFoundException(InternalErrorCode.TODO_NOT_FOUND, "The ToDo with ID %s not found".formatted(id.toString()));

        ToDoItemEntity entity = result.get();
        entity.setDeletedAt(LocalDateTime.now());

        toDoItemRepository.save(entity);
    }
}
