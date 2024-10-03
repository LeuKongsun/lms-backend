package com.kongsun.leanring.system.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginationDTO {
    private Integer pageSize;
    private Integer pageNumber;
    private Integer totalPages;
    private Integer numberOfElements;
    private Long totalElements;
    private Boolean first;
    private Boolean last;
    private Boolean empty;
}