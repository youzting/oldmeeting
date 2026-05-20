package com.example.meets.domain.member.controller;

import com.example.meets.common.dto.ApiResponse;
import com.example.meets.domain.member.dto.request.CreateMemberRequest;
import com.example.meets.domain.member.dto.request.UpdateMemberProfileRequest;
import com.example.meets.domain.member.dto.response.MemberSummaryResponse;
import com.example.meets.domain.member.service.MemberService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

  private final MemberService memberService;

  @PostMapping
  public ApiResponse<MemberSummaryResponse> create(@Valid @RequestBody CreateMemberRequest request) {
    return ApiResponse.created(memberService.create(request));
  }

  @GetMapping("/{memberId}")
  public ApiResponse<MemberSummaryResponse> get(@PathVariable Long memberId) {
    return ApiResponse.ok(memberService.get(memberId));
  }

  @GetMapping
  public ApiResponse<List<MemberSummaryResponse>> getAll() {
    return ApiResponse.ok(memberService.getAll());
  }

  @PatchMapping("/{memberId}/profile")
  public ApiResponse<MemberSummaryResponse> updateProfile(
      @PathVariable Long memberId, @Valid @RequestBody UpdateMemberProfileRequest request) {
    return ApiResponse.ok(memberService.updateProfile(memberId, request));
  }
}

