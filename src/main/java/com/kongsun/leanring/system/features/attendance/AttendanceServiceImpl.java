package com.kongsun.leanring.system.features.attendance;

import com.kongsun.leanring.system.features.attendance_detail.AttendanceDetail;
import com.kongsun.leanring.system.features.attendance_detail.AttendanceDetailMapper;
import com.kongsun.leanring.system.features.attendance_detail.AttendanceDetailRepository;
import com.kongsun.leanring.system.features.attendance_detail.AttendanceDetailResponse;
import com.kongsun.leanring.system.features.student.Student;
import com.kongsun.leanring.system.features.student.StudentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final AttendanceDetailRepository attendanceDetailRepository;
    private final StudentService studentService;
    private final AttendanceMapper attendanceMapper;
    private final AttendanceDetailMapper attendanceDetailMapper;

    @Override
    public AttendanceResponse create(AttendanceRequest attendanceRequest) {
        Attendance attendance = attendanceMapper.toAttendance(attendanceRequest);
        attendanceRepository.save(attendance);

        Map<AttendanceStatus, List<Long>> map = attendanceRequest.getAttendance();
        List<AttendanceDetail> attendanceDetails = map.entrySet().stream()
                .flatMap(entry -> {
                    AttendanceStatus status = entry.getKey();
                    List<Long> studentIds = entry.getValue();
                    List<Student> students = studentService.getByIds(studentIds);
                    return students.stream().map(stu ->
                            AttendanceDetail.builder()
                                    .attendance(attendance)
                                    .status(status)
                                    .student(stu)
                                    .date(attendanceRequest.getDate())
                                    .build()
                    );
                })
                .toList();

        attendanceDetailRepository.saveAll(attendanceDetails);
//        return getAttendanceResponse(attendance, attendanceDetails);
        return null;

    }

    @Override
    public List<?> getAll(Map<String, String> params) {
        Specification<Attendance> spec = Specification.where(null);
        if (params.containsKey("startDate") && params.containsKey("endDate")) {
            LocalDate startDate = LocalDate.parse(params.get("startDate"));
            LocalDate endDate = LocalDate.parse(params.get("endDate"));
            spec = AttendanceSpec.betweenDate(startDate, endDate);
        }
        if (params.containsKey("courseId")) {
            spec = AttendanceSpec.containCourseId(Long.parseLong(params.get("courseId")));
        }

//        Pageable pageable = PaginationUtil.getPageNumberAndPageSize(params);
        List<Attendance> attendances = attendanceRepository.findAll(spec);

        List<Long> attIds = attendances.stream().map(Attendance::getId).toList();
        List<AttendanceDetail> attDetails = attendanceDetailRepository.findByAttendanceIdIn(attIds);

        List<AttendanceResponse> attendanceMap = attendances.stream().map(attendanceMapper::toAttendanceResponse).toList();
        for (AttendanceResponse attendanceResponse : attendanceMap) {
            List<AttendanceDetailResponse> list = attDetails.stream()
                    .filter(detail -> detail.getAttendance().getId().equals(attendanceResponse.getId()))
                    .map(attendanceDetailMapper::toAttendanceDetailResponse)
                    .toList();

            attendanceResponse.setAttendanceDetails(list);
        }
        return attendanceMap;
//
//        List<Long> attIds = atts.stream().map(Attendance::getId).toList();
//        List<AttendanceDetail> attDetails = attendanceDetailRepository.findByAttendanceIdIn(attIds);
//
//        //grouping attDetails by attId
//        Map<Long, List<AttendanceDetail>> attendanceDetailsMap = attDetails.stream()
//                .collect(Collectors.groupingBy(attendanceDetail -> attendanceDetail.getAttendance().getId()));
//
//        //Map attRecord to attResponse
//        List<AttendanceResponse> attResponse = atts.stream()
//                .map(att -> {
//                    List<AttendanceDetail> attDetail = attendanceDetailsMap.getOrDefault(att.getId(), Collections.emptyList());
//
//                    return getAttendanceResponse(att, attDetail);
//                })
//                .toList();
//
//        return new PageDTO(new PageImpl<>(attResponse, pageable, atts.getTotalElements()));
    }

    @Override
    public AttendanceResponse getById(Long id) {
//        Attendance attendances = attendanceRepository.findById(id)
//                .orElseThrow(()-> new ResourceNotFoundException("Attendance", id));
//
//        List<AttendanceDetail> attendanceDetails = attendanceDetailRepository.findByAttendanceId(id);
//        return getAttendanceResponse(attendances, attendanceDetails);
        return null;
    }

//    private AttendanceResponse getAttendanceResponse(Attendance att, List<AttendanceDetail> attDetail) {
//        Map<AttendanceStatus, List<Long>> attendanceMap = attDetail.stream()
//                .collect(
//                        Collectors.groupingBy(
//                                AttendanceDetail::getAttendanceStatus,
//                                Collectors.mapping(
//                                        ad -> ad.getStudent().getId(), Collectors.toList()
//                                )
//                        )
//                );
//
//        AttendanceResponse attResponse = attendanceMapper.toAttendanceResponse(att);
//        attResponse.setAttendance(attendanceMap);
//        return attResponse;
//    }
}
