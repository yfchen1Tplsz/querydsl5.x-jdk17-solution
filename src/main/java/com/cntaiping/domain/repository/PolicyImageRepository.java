package com.cntaiping.domain.repository;

import com.cntaiping.domain.entity.PolicyImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PolicyImageRepository extends JpaRepository<PolicyImage, UUID> {
}
