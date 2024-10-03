package com.kongsun.leanring.system.attendance_detail;

import com.kongsun.leanring.system.common.PageDTO;
import com.kongsun.leanring.system.common.PaginationUtil;
import com.kongsun.leanring.system.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AttendanceDetailServiceImpl implements AttendanceDetailService {
    private final AttendanceDetailRepository attendanceDetailRepository;
    private final AttendanceDetailMapper attendanceDetailMapper;


    @Override
    public PageDTO getAttendanceDetailsByCourse(Map<String, String> params) {
        Specification<AttendanceDetail> spec = Specification.where(null);
        if (params.containsKey("courseId")) {
            long courseId = Long.parseLong(params.get("courseId"));
            spec = AttendanceDetailSpec.containCourseId(courseId);
        }
        if (params.containsKey("startDate") && !params.get("startDate").isEmpty() &&
                params.containsKey("endDate") && !params.get("endDate").isEmpty()) {
            LocalDate startDate = LocalDate.parse(params.get("startDate"));
            LocalDate endDate = LocalDate.parse(params.get("endDate"));
            spec = AttendanceDetailSpec.betweenDate(startDate, endDate);
        }

        Pageable pageable = PaginationUtil.getPageNumberAndPageSize(params);
        Page<AttendanceDetail> detailPage = attendanceDetailRepository.findAll(spec, pageable);
        List<AttendanceDetailResponse> detailResponse = detailPage.stream()
                .map(attendanceDetailMapper::toAttendanceDetailResponse)
                .toList();

        return new PageDTO(detailPage, detailResponse);
    }

    @Override
    public AttendanceDetail getById(Long attendanceDetailId) {
        return attendanceDetailRepository.findById(attendanceDetailId)
                .orElseThrow(() -> new ResourceNotFoundException("AttendanceDetail", attendanceDetailId));
    }

    @Override
    public void delete(Long attendanceDetailId) {
        getById(attendanceDetailId);
        attendanceDetailRepository.deleteById(attendanceDetailId);
    }

    @Override
    public void updateStatus(Long attendanceDetailId, UpdateStatusRequest request) {
        AttendanceDetail byId = getById(attendanceDetailId);
        byId.setStatus(request.getStatus());
        attendanceDetailRepository.save(byId);
    }
}
