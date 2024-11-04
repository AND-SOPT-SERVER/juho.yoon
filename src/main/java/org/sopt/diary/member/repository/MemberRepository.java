package org.sopt.diary.member.repository;

import java.util.Optional;
import org.sopt.diary.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);
}
