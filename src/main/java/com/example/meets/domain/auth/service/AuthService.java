package com.example.meets.domain.auth.service;

import com.example.meets.common.exception.ErrorCode;
import com.example.meets.common.exception.ServiceException;
import com.example.meets.domain.auth.dto.request.LoginRequest;
import com.example.meets.domain.auth.dto.request.SignUpRequest;
import com.example.meets.domain.auth.dto.response.AuthMemberResponse;
import com.example.meets.domain.member.dto.response.MemberSummaryResponse;
import com.example.meets.domain.member.entity.Member;
import com.example.meets.domain.member.repository.MemberRepository;
import com.example.meets.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

  private final MemberService memberService;
  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public AuthMemberResponse signUp(SignUpRequest request) {
    MemberSummaryResponse member = memberService.create(request.toCreateMemberRequest());
    return AuthMemberResponse.of("회원가입이 완료되었습니다.", member);
  }

  public AuthMemberResponse login(LoginRequest request) {
    Member member =
        memberRepository
            .findByEmail(request.email())
            .orElseThrow(() -> new ServiceException(ErrorCode.INVALID_CREDENTIALS));

    if (!passwordEncoder.matches(request.password(), member.getPassword())) {
      throw new ServiceException(ErrorCode.INVALID_CREDENTIALS);
    }

    return AuthMemberResponse.of("로그인에 성공했습니다.", MemberSummaryResponse.from(member));
  }
}
