package com.kongsun.leanring.system.features.category;

import com.kongsun.leanring.system.common.PageDTO;

import java.util.List;
import java.util.Map;


public interface CategoryService {
    Category create(Category category);
    Category getById(Long id);
    Category update(Long id, Category category);
    void deleteById(Long id);
    PageDTO getAll(Map<String, String> params);
    List<Category> getAll();
}
