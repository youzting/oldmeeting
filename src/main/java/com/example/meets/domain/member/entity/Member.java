package com.example.meets.domain.member.entity;

import com.example.meets.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 100)
  private String email;

  @Column(nullable = false, length = 30)
  private String nickname;

  @Column(nullable = false)
  private LocalDate birthDate;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private Gender gender;

  @Column(nullable = false, length = 300)
  private String bio;

  @Column(nullable = false)
  private boolean active;

  @Builder
  private Member(String email, String nickname, LocalDate birthDate, Gender gender, String bio) {
    this.email = email;
    this.nickname = nickname;
    this.birthDate = birthDate;
    this.gender = gender;
    this.bio = bio;
    this.active = true;
  }

  public void updateProfile(String nickname, String bio) {
    this.nickname = nickname;
    this.bio = bio;
  }
}

