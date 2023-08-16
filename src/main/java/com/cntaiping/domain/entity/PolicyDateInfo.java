package com.cntaiping.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "t_policy_date_info",schema = "gis")
@Comment(value = "保单日期信息表")
public class PolicyDateInfo  {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Comment(value = "id")
    @Column(nullable = false)
    private UUID id;

    @Comment(value = "保单id")
    @Column(length = 36, columnDefinition = "uuid", nullable = false)
    private UUID policyId;

    @Comment(value = "申请日期")
    @Column(name = "proposal_date")
    private LocalDate applicationDate;

    @Comment(value = "保单生效日期")
    @Column(name = "commencement_date")
    private LocalDate policyEffectDate;

    @Comment(value = "保障生效日期")
    @Column(name = "benefit_effect_date")
    private LocalDate ensureEffectDate;

    @Comment(value = "保障终止日期")
    @Column(name = "termination_date")
    private LocalDate ensureExpireDate;

    @Comment(value = "保单通过日期")
    @Column(name = "next_payment_date")
    private LocalDate policyAdoptionDate;

    @Comment(value = "软删除标识")
    @Column(nullable = false, columnDefinition = "bool default false")
    private Boolean deleted;

}