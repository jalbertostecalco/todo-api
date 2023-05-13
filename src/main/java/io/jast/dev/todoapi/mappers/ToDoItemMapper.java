package io.jast.dev.todoapi.mappers;

import io.jast.dev.todoapi.dtos.ToDoItemRequestDTO;
import io.jast.dev.todoapi.dtos.ToDoItemResponseDTO;
import io.jast.dev.todoapi.entities.ToDoItemEntity;

import java.time.LocalDateTime;

public class ToDoItemMapper {

    public static ToDoItemResponseDTO toDto(ToDoItemEntity entity) {
        return new ToDoItemResponseDTO(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getPlannedAt());
    }

    public static ToDoItemEntity toEntity(ToDoItemRequestDTO dto) {
        ToDoItemEntity entity = new ToDoItemEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setPlannedAt(LocalDateTime.now()); //TODO: Leer de la vista despues
        entity.setCreatedAt(LocalDateTime.now());

        return entity;
    }
}