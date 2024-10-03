package com.kongsun.leanring.system.category;

import com.kongsun.leanring.system.common.PageDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

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
