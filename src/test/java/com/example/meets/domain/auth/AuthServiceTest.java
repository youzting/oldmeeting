package com.example.meets.domain.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.meets.common.exception.ServiceException;
import com.example.meets.domain.auth.dto.request.LoginRequest;
import com.example.meets.domain.auth.dto.request.SignUpRequest;
import com.example.meets.domain.auth.dto.response.AuthMemberResponse;
import com.example.meets.domain.auth.service.AuthService;
import com.example.meets.domain.member.entity.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class AuthServiceTest {

  @Autowired private AuthService authService;

  @Test
  void signUpAndLoginSuccess() {
    SignUpRequest signUpRequest =
        new SignUpRequest(
            "alice@example.com",
            "alice",
            "password123",
            java.time.LocalDate.of(1998, 3, 15),
            Gender.FEMALE,
            "안녕하세요.");

    AuthMemberResponse signUpResponse = authService.signUp(signUpRequest);
    AuthMemberResponse loginResponse =
        authService.login(new LoginRequest("alice@example.com", "password123"));

    assertThat(signUpResponse.message()).isEqualTo("회원가입이 완료되었습니다.");
    assertThat(signUpResponse.member().email()).isEqualTo("alice@example.com");
    assertThat(loginResponse.message()).isEqualTo("로그인에 성공했습니다.");
    assertThat(loginResponse.member().nickname()).isEqualTo("alice");
  }

  @Test
  void loginFailsWhenPasswordIsWrong() {
    authService.signUp(
        new SignUpRequest(
            "bob@example.com",
            "bob",
            "password123",
            java.time.LocalDate.of(1997, 8, 21),
            Gender.MALE,
            "반갑습니다."));

    assertThatThrownBy(() -> authService.login(new LoginRequest("bob@example.com", "wrongpass123")))
        .isInstanceOf(ServiceException.class)
        .hasMessage("이메일 또는 비밀번호가 올바르지 않습니다.");
  }
}
