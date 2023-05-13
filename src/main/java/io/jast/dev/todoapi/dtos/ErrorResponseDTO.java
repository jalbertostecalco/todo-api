package io.jast.dev.todoapi.dtos;

import io.jast.dev.todoapi.enums.InternalErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ErrorResponseDTO {
    private InternalErrorCode code;
    private String message;
    private List<String> details;
}