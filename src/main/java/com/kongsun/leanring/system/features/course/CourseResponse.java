package com.kongsun.leanring.system.features.course;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CourseResponse {
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private BigDecimal discount;

    private String image;

    private Long categoryId;

    private Long teacherId;

}
