package com.kongsun.leanring.system.category;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryRequest {
    @NotNull(message = "name is required")
    private String name;
    private String description;
}
