package com.cntaiping.domain.repository;

import com.cntaiping.domain.entity.PolicyComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PolicyCommentRepository extends JpaRepository<PolicyComment, UUID> {
}
