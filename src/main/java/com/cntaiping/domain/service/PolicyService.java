package com.cntaiping.domain.service;

import com.cntaiping.domain.entity.*;
import com.cntaiping.domain.repository.PolicyCommentRepository;
import com.cntaiping.domain.repository.PolicyDateRepository;
import com.cntaiping.domain.repository.PolicyImageRepository;
import com.cntaiping.domain.repository.PolicyRepository;
import com.cntaiping.domain.vo.PolicyAllView;
import com.cntaiping.domain.vo.PolicyBo;
import com.cntaiping.domain.vo.QPolicyAllView;
import com.cntaiping.domain.vo.QPolicyBo;
import com.github.javafaker.Faker;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class PolicyService {
    @Autowired
    private PolicyRepository policyRepository;
    @Autowired
    private PolicyDateRepository policyDateRepository;
    @Autowired
    private PolicyCommentRepository policyCommentRepository;
    @Autowired
    private PolicyImageRepository policyImageRepository;
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    //插入一些faker数据
    public void insertFakerData(int nums){
        Faker faker = new Faker();

        for (int i = 0; i< nums;i++) {
            PolicyEntity policyEntity = new PolicyEntity();
            policyEntity.setPolicyNo(faker.number().digits(10));
            policyEntity.setPolicyStatus(getRandomPolicyStatus());
            policyEntity.setPolicyCurrency(faker.currency().code());
            policyEntity.setPolicyPremium(BigDecimal.valueOf(faker.number().randomDouble(2, 100, 1000)));
            policyEntity.setPolicyAmount(BigDecimal.valueOf(faker.number().randomDouble(2, 1000, 10000)));
            policyEntity.setEffective(faker.random().nextBoolean());
            policyEntity.setDeleted(false);
            PolicyEntity entityReturn = policyRepository.save(policyEntity);

            PolicyDateInfo policyDateInfo = new PolicyDateInfo();
            policyDateInfo.setPolicyId(entityReturn.getId());
            policyDateInfo.setApplicationDate(faker.date().past(365, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            policyDateInfo.setPolicyEffectDate(faker.date().future(365, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            policyDateInfo.setEnsureEffectDate(faker.date().future(365, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            policyDateInfo.setEnsureExpireDate(faker.date().future(365, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            policyDateInfo.setPolicyAdoptionDate(faker.date().future(365, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            policyDateInfo.setDeleted(false);
            policyDateRepository.save(policyDateInfo);

            int commentNums = (int)(Math.random()*5+1);
            for(int j =0; j< commentNums; j++){
                PolicyComment policyComment = new PolicyComment();
                policyComment.setPolicyId(entityReturn.getId());
                policyComment.setCommentTime(faker.date().past(365,TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                policyComment.setCommentUser(faker.name().fullName());
                policyComment.setCommentContent(faker.lorem().sentence());
                policyComment.setSaveInd("F");
                policyComment.setDeleted(false);
                policyCommentRepository.save(policyComment);
            }
            int imageNums = (int)(Math.random()*5+1);
            for(int k = 0; k<imageNums; k++){
                PolicyImage policyImage = new PolicyImage();
                policyImage.setPolicyId(entityReturn.getId());
                policyImage.setFileId(UUID.randomUUID());
                policyImage.setImageFileName(faker.file().fileName());
                policyImage.setImageFilePath(faker.file().extension());
                policyImage.setMainDocType(faker.lorem().word());
                policyImage.setSubDocType(faker.lorem().word());
                policyImage.setImageUser(faker.name().fullName());
                policyImage.setImageDate(faker.date().past(365,TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                policyImage.setDeleted(false);
                policyImageRepository.save(policyImage);
            }
        }

    }

    private static String getRandomPolicyStatus() {
        String[] policyStatuses = {"NB", "UW", "PRP", "STD", "SUB", "ISSUE", "POS"};
        Faker faker = new Faker();
        int randomIndex = faker.random().nextInt(policyStatuses.length);
        return policyStatuses[randomIndex];
    }


    //1 演示简单查询 querying
    public List<PolicyEntity> singleQuery01(String policy, BigDecimal amount){
        QPolicyEntity qPolicyEntity = QPolicyEntity.policyEntity;
        return  jpaQueryFactory.selectFrom(qPolicyEntity)
                .where((qPolicyEntity.policyNo.eq(policy).or(qPolicyEntity.policyAmount.gt(amount))).and(qPolicyEntity.deleted.not()))
                .orderBy(qPolicyEntity.effective.desc(),qPolicyEntity.policyPremium.asc())
                .fetch();
    }

    //2 演示多数据源查询
    public List<PolicyAllView> multipleSourceQuery01(){
        QPolicyEntity qPolicyEntity = QPolicyEntity.policyEntity;
        QPolicyDateInfo qPolicyDateInfo = QPolicyDateInfo.policyDateInfo;

        return jpaQueryFactory.select(
                    new QPolicyAllView(
                            qPolicyEntity.id,
                            qPolicyEntity.policyNo,
                            qPolicyEntity.policyStatus,
                            qPolicyEntity.policyCurrency,
                            qPolicyEntity.policyPremium,
                            qPolicyEntity.policyAmount,
                            qPolicyEntity.effective,
                            qPolicyEntity.deleted,
                            qPolicyDateInfo.applicationDate,
                            qPolicyDateInfo.policyEffectDate,
                            qPolicyDateInfo.ensureEffectDate,
                            qPolicyDateInfo.ensureExpireDate,
                            qPolicyDateInfo.policyAdoptionDate
                    )
                )
                .from(qPolicyEntity,qPolicyDateInfo)
                .where(qPolicyEntity.id.eq(qPolicyDateInfo.policyId))
                .orderBy(qPolicyDateInfo.applicationDate.asc())
                .fetch();
    }

    //3 演示连表查询 using joins -- Projections.constructor
    public List<PolicyBo> projectionsQueryByConstructor(){
        QPolicyEntity qPolicyEntity = QPolicyEntity.policyEntity;
        QPolicyDateInfo qPolicyDateInfo = QPolicyDateInfo.policyDateInfo;
        return jpaQueryFactory.select(
                        new QPolicyBo(
                                qPolicyEntity.id,
                                qPolicyEntity.policyNo,
                                qPolicyEntity.policyStatus,
                                qPolicyEntity.policyCurrency,
                                qPolicyEntity.policyPremium,
                                qPolicyEntity.policyAmount,
                                qPolicyEntity.effective,
                                qPolicyDateInfo.applicationDate,
                                qPolicyDateInfo.policyEffectDate,
                                qPolicyDateInfo.policyAdoptionDate
                        )
                ).from(qPolicyEntity)
                .leftJoin(qPolicyDateInfo)
                .on(qPolicyEntity.id.eq(qPolicyDateInfo.policyId).and(qPolicyDateInfo.deleted.isFalse()))
                .where(qPolicyEntity.deleted.isFalse().and(qPolicyDateInfo.ensureEffectDate.after(LocalDate.of(2023,12,1))))
                .fetch();

    }

    //3 演示子查询与分组查询 --JPAExpressions /GroupBy
    public List<PolicyEntity> findGreaterThanAvgPremium(){
        QPolicyEntity qPolicyEntity = QPolicyEntity.policyEntity;

        return jpaQueryFactory.selectFrom(qPolicyEntity)
                .where(
                        qPolicyEntity.policyPremium
                        .gt(JPAExpressions.select(qPolicyEntity.policyPremium.avg()).from(qPolicyEntity))
                ).fetch();
    }


}
