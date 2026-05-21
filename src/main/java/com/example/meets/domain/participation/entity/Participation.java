package com.example.meets.domain.participation.entity;

import com.example.meets.common.entity.BaseEntity;
import com.example.meets.domain.hobby.entity.HobbyGroup;
import com.example.meets.domain.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(
    name = "participations",
    uniqueConstraints =
        @UniqueConstraint(
            name = "uk_participation_member_group",
            columnNames = {"member_id", "hobby_group_id"}))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Participation extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "hobby_group_id", nullable = false)
  private HobbyGroup hobbyGroup;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private ParticipationStatus status;

  @Builder
  private Participation(Member member, HobbyGroup hobbyGroup) {
    this.member = member;
    this.hobbyGroup = hobbyGroup;
    this.status = ParticipationStatus.APPROVED;
  }

  public void changeStatus(ParticipationStatus status) {
    this.status = status;
  }
}
