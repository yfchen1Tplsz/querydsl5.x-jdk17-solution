package com.cntaiping.config;


import com.querydsl.jpa.hibernate.HibernateQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.sql.PostgreSQLTemplates;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLTemplates;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager){
        return new JPAQueryFactory(entityManager);
    }

    //但一般来说不会这样创建出JPAQuery，使用JPAQueryFactory的api来创建JPAQuery是更好的选择
/*    @Bean
    public JPAQuery<?> jpaQuery(EntityManager entityManager){
        return new JPAQuery<Void>(entityManager);
    }*/

    //注入pg本地SQLQueryFactory
    @Bean
    public SQLQueryFactory sqlQueryFactory(DataSource dataSource){
        SQLTemplates pgyTemplate = new PostgreSQLTemplates();
        com.querydsl.sql.Configuration configuration = new com.querydsl.sql.Configuration(pgyTemplate);
        return  new SQLQueryFactory(configuration,dataSource);
    }
}
