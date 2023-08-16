package com.cntaiping.domain.repository;

import com.cntaiping.domain.entity.PolicyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PolicyRepository extends
        JpaRepository<PolicyEntity, UUID>,
        QuerydslPredicateExecutor<PolicyEntity> {
}
