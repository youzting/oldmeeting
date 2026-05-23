package com.example.meets.domain.auth.dto.response;

import com.example.meets.domain.member.dto.response.MemberSummaryResponse;

public record AuthMemberResponse(String message, MemberSummaryResponse member) {

  public static AuthMemberResponse of(String message, MemberSummaryResponse member) {
    return new AuthMemberResponse(message, member);
  }
}
