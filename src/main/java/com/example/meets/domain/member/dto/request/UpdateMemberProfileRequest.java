package com.example.meets.domain.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateMemberProfileRequest(
    @NotBlank(message = "닉네임은 필수입니다.")
        @Size(max = 30, message = "닉네임은 30자 이하여야 합니다.")
        String nickname,
    @NotBlank(message = "소개글은 필수입니다.")
        @Size(max = 300, message = "소개글은 300자 이하여야 합니다.")
        String bio) {}

