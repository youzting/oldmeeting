package com.example.meets.domain.member.dto.response;

import com.example.meets.domain.member.entity.Gender;
import com.example.meets.domain.member.entity.Member;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record MemberSummaryResponse(
    Long id,
    String email,
    String nickname,
    LocalDate birthDate,
    Gender gender,
    String bio,
    boolean active,
    LocalDateTime createdAt,
    LocalDateTime modifiedAt) {

  public static MemberSummaryResponse from(Member member) {
    return new MemberSummaryResponse(
        member.getId(),
        member.getEmail(),
        member.getNickname(),
        member.getBirthDate(),
        member.getGender(),
        member.getBio(),
        member.isActive(),
        member.getCreatedAt(),
        member.getModifiedAt());
  }
}

