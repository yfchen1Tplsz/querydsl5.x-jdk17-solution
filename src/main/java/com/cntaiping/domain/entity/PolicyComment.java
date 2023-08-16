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
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "t_policy_comment",schema = "gis")
@Comment(value = "保单批注表")
public class PolicyComment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false) //columnDefinition = "uuid default uuid_generate_v4()",
    @Comment(value = "主键id")
    private UUID id;

    @Column(nullable =false)
    @Comment(value = "保单id")
    private UUID policyId;

    @Column
    @Comment(value = "批注时间")
    private LocalDateTime commentTime;

    @Column(length = 500)
    @Comment(value = "批注用户")
    private String commentUser;

    @Column(columnDefinition = "text default ''")
    @Comment(value = "批注内容")
    private String commentContent;

    @Column(length = 1,columnDefinition = "varchar(1) default 'F'")
    @Comment(value = "是否暂存")
    private String saveInd;

    @Comment(value = "软删除标识")
    @Column(nullable = false, columnDefinition = "bool default false")
    private Boolean deleted;
}
