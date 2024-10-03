package com.kongsun.leanring.system.category;

import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface CategoryMapper {
    Category toCategory(CategoryRequest request);

    CategoryResponse toCategoryResponse(Category category);
}
