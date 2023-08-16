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
@Table(name = "t_policy_image",schema = "gis")
@Comment(value = "保单影像信息表")
public class PolicyImage {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false) //columnDefinition = "uuid default uuid_generate_v4()",
    @Comment(value = "主键id")
    private UUID id;

    @Column(nullable =false)
    @Comment(value = "保单id")
    private UUID policyId;

    @Column
    @Comment(value = "文件id(content服务生成)")
    private UUID fileId;

    @Column(length = 256)
    @Comment(value = "上传影像文件名")
    private String imageFileName;

    @Column(length = 500)
    @Comment(value = "上传影像文件路径")
    private String imageFilePath;

    @Column(length = 20)
    @Comment(value = "主单证类型")
    private String mainDocType;

    @Column(length = 20)
    @Comment(value = "副单证类型")
    private String subDocType;

    @Column(length = 500)
    @Comment(value = "添加影像用户")
    private String imageUser;

    @Column
    @Comment(value = "添加影像日期")
    private LocalDate imageDate;

    @Comment(value = "软删除标识")
    @Column(nullable = false, columnDefinition = "bool default false")
    private Boolean deleted;
}
