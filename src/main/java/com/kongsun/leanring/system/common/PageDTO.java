package com.kongsun.leanring.system.common;

import com.kongsun.leanring.system.payment.Payment;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PageDTO {
    private List<?> data;
    private PaginationDTO pagination;


    public PageDTO(Page<?> page) {
        data = page.getContent();
        pagination = PaginationDTO.builder()
                .empty(page.isEmpty())
                .first(page.isFirst())
                .last(page.isLast())
                .pageSize(page.getPageable().getPageSize())
                .pageNumber(page.getPageable().getPageNumber() + 1)
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .numberOfElements(page.getNumberOfElements())
                .build();

    }
    public PageDTO(Page<?> page, List<?> data) {
        this.data = data;
        pagination = PaginationDTO.builder()
                .empty(page.isEmpty())
                .first(page.isFirst())
                .last(page.isLast())
                .pageSize(page.getPageable().getPageSize())
                .pageNumber(page.getPageable().getPageNumber() + 1)
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .numberOfElements(page.getNumberOfElements())
                .build();

    }

    public PageDTO(List<?> list) {
        data = list;
        pagination = PaginationDTO.builder()
                .empty(list.isEmpty())
                .first(true)
                .last(true)
                .pageSize(list.size())
                .pageNumber(1)
                .totalPages(1)
                .totalElements((long) list.size())
                .numberOfElements(list.size())
                .build();
    }

}