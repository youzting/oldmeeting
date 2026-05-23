package com.example.meets.domain.auth.dto.request;

import com.example.meets.domain.member.dto.request.CreateMemberRequest;
import com.example.meets.domain.member.entity.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record SignUpRequest(
    @NotBlank(message = "이메일은 필수입니다.") @Email(message = "올바른 이메일 형식이 아닙니다.") String email,
    @NotBlank(message = "닉네임은 필수입니다.")
        @Size(max = 30, message = "닉네임은 30자 이하여야 합니다.")
        String nickname,
    @NotBlank(message = "비밀번호는 필수입니다.")
        @Size(min = 8, max = 100, message = "비밀번호는 8자 이상 100자 이하여야 합니다.")
        String password,
    @NotNull(message = "생년월일은 필수입니다.") @Past(message = "생년월일은 과거 날짜여야 합니다.") LocalDate birthDate,
    @NotNull(message = "성별은 필수입니다.") Gender gender,
    @NotBlank(message = "소개글은 필수입니다.")
        @Size(max = 300, message = "소개글은 300자 이하여야 합니다.")
        String bio) {

  public CreateMemberRequest toCreateMemberRequest() {
    return new CreateMemberRequest(email, nickname, password, birthDate, gender, bio);
  }
}
