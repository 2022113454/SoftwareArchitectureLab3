package org.example.mygo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "org.example.mygo.mycat", sqlSessionTemplateRef = "mycatSqlSessionTemplate")
public class MycatDataSourceConfig {

    @Bean(name = "mycatDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mycat")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mycatSqlSessionFactory")
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("mycatDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        return sessionFactory.getObject();
    }

    @Bean(name = "mycatSqlSessionTemplate")
    public SqlSessionTemplate primarySqlSessionTemplate(@Qualifier("mycatSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
