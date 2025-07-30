package com.devtiro.tasks.domain.entities.dto;
import java.util.UUID;

public record TaskListDto(
        UUID id,
        String title,
        String description,
        java.util.List<Object> objects) {


    public Object tasks() {
        return null;
    }
}
