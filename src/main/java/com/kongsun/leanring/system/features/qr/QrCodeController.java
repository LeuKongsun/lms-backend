package com.kongsun.leanring.system.features.qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.kongsun.leanring.system.exception.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/qr")
@RequiredArgsConstructor
@PreAuthorize("permitAll()")
public class QrCodeController {

    @Value("${qr.frontend-url}")
    private String frontendUrl;
    @GetMapping(value = "/generateQRCode", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> barbecueEAN13Barcode(@RequestBody Map<String, String> requestData) throws WriterException {
        String courseId = requestData.get("courseId");
        String date = requestData.get("date");

        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        String qrContent = frontendUrl + "/qr?courseId="+ courseId + "&date="+date;

        BitMatrix bitMatrix = qrCodeWriter.encode(qrContent, BarcodeFormat.QR_CODE, 200, 200);

        return new ResponseEntity<>(MatrixToImageWriter.toBufferedImage(bitMatrix), HttpStatus.OK);
    }

    @GetMapping("testing")
    public ResponseEntity<?> getTesting() {
        return ResponseEntity.ok("testing");
    }

    @PostMapping("/submitAttendance")
    public ResponseEntity<ApiResponse> submitAttendance(@RequestBody SubmitAttendanceRequest submitAttendanceRequest) {
        // Logic to mark attendance in the database
//        boolean success = attendanceService.markAttendance(classId, studentId);

        System.out.println(submitAttendanceRequest);
        // handle update attendance status
        boolean success = true;

        return ResponseEntity
                .status(CREATED)
                .body(ApiResponse.builder()
                        .data(null)
                        .message("create attendance successful")
                        .httpStatus(CREATED.value())
                        .build()
                );
    }

}
