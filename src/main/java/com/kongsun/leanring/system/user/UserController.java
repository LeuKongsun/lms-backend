package com.kongsun.leanring.system.user;

import com.kongsun.leanring.system.exception.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@RestController
@PreAuthorize("hasRole('ADMIN')")
public class UserController {
    private final UserMapper userMapper;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        List<User> users = userService.getAll();
        return ResponseEntity
                .ok(ApiResponse.builder()
                        .data(users.stream().map(userMapper::toUserResponse))
                        .message("get users successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }
}
