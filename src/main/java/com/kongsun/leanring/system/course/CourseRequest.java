package com.kongsun.leanring.system.course;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CourseRequest {
    @NotNull(message = "name is required")
    private String name;

    private String description;

    @PositiveOrZero(message = "Salary must be positive or zero")
    private BigDecimal price;

    private BigDecimal discount;

    private String image;

    @NotNull(message = "categoryId is required")
    private Long categoryId;

    private Long teacherId;


}
