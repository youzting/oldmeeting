package com.example.meets.domain.member.service;

import com.example.meets.common.exception.ErrorCode;
import com.example.meets.common.exception.ServiceException;
import com.example.meets.domain.member.dto.request.CreateMemberRequest;
import com.example.meets.domain.member.dto.request.UpdateMemberProfileRequest;
import com.example.meets.domain.member.dto.response.MemberSummaryResponse;
import com.example.meets.domain.member.entity.Member;
import com.example.meets.domain.member.repository.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

  private final MemberRepository memberRepository;

  @Transactional
  public MemberSummaryResponse create(CreateMemberRequest request) {
    if (memberRepository.existsByEmail(request.email())) {
      throw new ServiceException(ErrorCode.DUPLICATE_EMAIL);
    }

    Member member =
        Member.builder()
            .email(request.email())
            .nickname(request.nickname())
            .birthDate(request.birthDate())
            .gender(request.gender())
            .bio(request.bio())
            .build();

    return MemberSummaryResponse.from(memberRepository.save(member));
  }

  public MemberSummaryResponse get(Long memberId) {
    return MemberSummaryResponse.from(findMember(memberId));
  }

  public List<MemberSummaryResponse> getAll() {
    return memberRepository.findAll(Sort.by(Sort.Direction.DESC, "id")).stream()
        .map(MemberSummaryResponse::from)
        .toList();
  }

  @Transactional
  public MemberSummaryResponse updateProfile(Long memberId, UpdateMemberProfileRequest request) {
    Member member = findMember(memberId);
    member.updateProfile(request.nickname(), request.bio());
    return MemberSummaryResponse.from(member);
  }

  private Member findMember(Long memberId) {
    return memberRepository
        .findById(memberId)
        .orElseThrow(() -> new ServiceException(ErrorCode.MEMBER_NOT_FOUND));
  }
}
