package com.kongsun.leanring.system.category;

import com.kongsun.leanring.system.common.PageDTO;
import com.kongsun.leanring.system.exception.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;


    @GetMapping
    public ResponseEntity<PageDTO> getAll(@RequestParam Map<String, String> params) {
        return ResponseEntity
                .ok(categoryService.getAll(params));

    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAll() {
        List<Category> categories = categoryService.getAll();
        return ResponseEntity
                .status(CREATED)
                .body(ApiResponse.builder()
                        .data(categories.stream().map(categoryMapper::toCategoryResponse).toList())
                        .message("get all category successfully")
                        .httpStatus(CREATED.value())
                        .build()
                );

    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid CategoryRequest request) {
        Category category = categoryMapper.toCategory(request);
        category = categoryService.create(category);
        return ResponseEntity
                .status(CREATED)
                .body(ApiResponse.builder()
                        .data(categoryMapper.toCategoryResponse(category))
                        .message("create category successfully")
                        .httpStatus(CREATED.value())
                        .build()
                );

    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Long id) {
        Category category = categoryService.getById(id);
        return ResponseEntity
                .ok(ApiResponse.builder()
                        .data(categoryMapper.toCategoryResponse(category))
                        .message("get category successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody @Valid CategoryRequest request) {
        Category category = categoryMapper.toCategory(request);
        category = categoryService.update(id, category);
        return ResponseEntity
                .ok()
                .body(ApiResponse.builder()
                        .data(categoryMapper.toCategoryResponse(category))
                        .message("update category successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity
                .ok(ApiResponse.builder()
                        .data(null)
                        .message("delete category successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

}
