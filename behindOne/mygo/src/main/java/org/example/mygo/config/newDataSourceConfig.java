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
@MapperScan(basePackages = "org.example.mygo.news", sqlSessionTemplateRef = "newSqlSessionTemplate")
public class newDataSourceConfig {

    @Bean(name = "newDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.news")
    public DataSource newDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "newSqlSessionFactory")
    public SqlSessionFactory newSqlSessionFactory(@Qualifier("newDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        return sessionFactory.getObject();
    }

    @Bean(name = "newSqlSessionTemplate")
    public SqlSessionTemplate newSqlSessionTemplate(@Qualifier("newSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
