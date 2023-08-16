package com.cntaiping;

import com.cntaiping.domain.entity.PolicyEntity;
import com.cntaiping.domain.entity.QPolicyEntity;
import com.cntaiping.domain.repository.PolicyRepository;
import com.cntaiping.domain.service.PolicyService;
import com.cntaiping.domain.vo.PolicyBo;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.sql.Configuration;
import com.querydsl.sql.PostgreSQLTemplates;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLTemplates;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Commit;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

@Slf4j
@SpringBootTest
class SolutionQuerydslGisApplicationTests {
    @Autowired
    private PolicyService policyService;
    @Autowired
    private SQLQueryFactory sqlQueryFactory;

    @Test
    @Commit
    @Transactional
    void insertFakerData(){
        policyService.insertFakerData(50);
    }

    @Test
    void testQuerydsl01() {
        policyService.singleQuery01("1080114723", BigDecimal.valueOf(20000.00))
                .stream().forEach(k-> System.out.println(k.toString()));
    }

    @Test
    void testMultiSourceQuerydsl01() {
        policyService.multipleSourceQuery01()
                .stream().forEach(k-> System.out.println(k.toString()));
    }

    @Test
    public void projectionsQuery01(){
        List<PolicyBo> policyBos = policyService.projectionsQueryByConstructor();
        policyBos.forEach(k-> System.out.println(k.toString()));
    }

    @Test
    public void subQueryAndGroupBy(){
        List<PolicyEntity> policyEntity = policyService.findGreaterThanAvgPremium();
        policyEntity.forEach(k-> System.out.println(k.toString()));
    }

/*    @Test
    public void testPgTemplate(){
        SQLTemplates pgTemplate = new PostgreSQLTemplates();
        Configuration configuration = new Configuration(pgTemplate);
        PostgreSQLTemplates.builder()
                .printSchema()
                .quote()
                .newLineToSingleSpace()
                .escape('\n')
                .build();
        QPolicyEntity qPolicyEntity = QPolicyEntity.policyEntity;
        sqlQueryFactory.select(qPolicyEntity)
    }*/
}
