package com.cntaiping.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "t_policy",schema = "gis")
@Comment(value = "保单实体表")
public class PolicyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false) //columnDefinition = "uuid default uuid_generate_v4()",
    @Comment(value = "主键id")
    private UUID id;

    @Column(length = 50,nullable = false)
    @Comment(value = "保单号")
    private String policyNo;

    @Column(length  = 20)
    @Comment(value = "保单状态")
    private String policyStatus;

    @Column(length  = 20)
    @Comment(value = "保单货币")
    private String policyCurrency;

    @Column(precision = 20, scale = 2)
    @Comment(value = "保单总保费")
    private BigDecimal policyPremium;

    @Column(precision = 20, scale = 2)
    @Comment(value = "保单保额")
    private BigDecimal policyAmount;


    @Column
    @Comment(value = "是否生效")
    private Boolean effective;

    @Comment(value = "软删除标识")
    @Column(nullable = false, columnDefinition = "bool default false")
    private Boolean deleted;
}
