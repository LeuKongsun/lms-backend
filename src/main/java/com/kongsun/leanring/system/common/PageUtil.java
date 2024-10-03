package com.kongsun.leanring.system.common;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface PageUtil {
    int DEFAULT_PAGE_NUMBER = 1;
    int DEFAULT_PAGE_SIZE = 10;

    String PAGE_NUMBER = "page";
    String PAGE_SIZE = "size";

    static Pageable getPageable(int pageNumber, int pageSize) {
        return getPageable(pageNumber, pageSize, "createdAt", "desc");
    }

    static Pageable getPageable(int pageNumber, int pageSize, String sortField, String sortDirection) {
        if (pageNumber < DEFAULT_PAGE_NUMBER) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortField);
        return PageRequest.of(pageNumber - 1, pageSize, sort);
    }

    static Pageable getPageable(int pageNumber, int pageSize, Sort sort) {
        if (pageNumber < DEFAULT_PAGE_NUMBER) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        return PageRequest.of(pageNumber - 1, pageSize, sort);
    }


}