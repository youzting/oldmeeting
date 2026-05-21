package com.example.meets.domain.hobby.entity;

import com.example.meets.common.entity.BaseEntity;
import com.example.meets.domain.member.entity.Member;
import com.example.meets.domain.participation.entity.Participation;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "hobby_groups")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HobbyGroup extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "host_member_id", nullable = false)
  private Member host;

  @Column(nullable = false, length = 50)
  private String title;

  @Column(nullable = false, length = 500)
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 30)
  private HobbyCategory category;

  @Column(nullable = false, length = 100)
  private String location;

  @Column(nullable = false)
  private LocalDateTime meetingAt;

  @Column(nullable = false)
  private int maxParticipants;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private HobbyGroupStatus status;

  @OneToMany(mappedBy = "hobbyGroup")
  private List<Participation> participations = new ArrayList<>();

  @Builder
  private HobbyGroup(
      Member host,
      String title,
      String description,
      HobbyCategory category,
      String location,
      LocalDateTime meetingAt,
      int maxParticipants) {
    this.host = host;
    this.title = title;
    this.description = description;
    this.category = category;
    this.location = location;
    this.meetingAt = meetingAt;
    this.maxParticipants = maxParticipants;
    this.status = HobbyGroupStatus.RECRUITING;
  }

  public void updateGroupInfo(
      String title,
      String description,
      HobbyCategory category,
      String location,
      LocalDateTime meetingAt,
      int maxParticipants) {
    this.title = title;
    this.description = description;
    this.category = category;
    this.location = location;
    this.meetingAt = meetingAt;
    this.maxParticipants = maxParticipants;
  }

  public void changeStatus(HobbyGroupStatus status) {
    this.status = status;
  }
}
