package com.example.meets.domain.auth.controller;

import com.example.meets.common.dto.ApiResponse;
import com.example.meets.domain.auth.dto.request.LoginRequest;
import com.example.meets.domain.auth.dto.request.SignUpRequest;
import com.example.meets.domain.auth.dto.response.AuthMemberResponse;
import com.example.meets.domain.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/signup")
  public ApiResponse<AuthMemberResponse> signUp(@Valid @RequestBody SignUpRequest request) {
    return ApiResponse.created(authService.signUp(request));
  }

  @PostMapping("/login")
  public ApiResponse<AuthMemberResponse> login(@Valid @RequestBody LoginRequest request) {
    return ApiResponse.ok(authService.login(request));
  }
}
