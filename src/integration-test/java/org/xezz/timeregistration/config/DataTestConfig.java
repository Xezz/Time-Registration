package org.xezz.timeregistration.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.OpenJpaDialect;
import org.springframework.orm.jpa.vendor.OpenJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * User: Xezz
 * Date: 12.06.13
 * Time: 23:16
 * A spring configuration class to enable testing of database stuff!
 */
@Configuration
@Profile("integration-test")
public class DataTestConfig {

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUrl("jdbc:hsqldb:mem:paging");
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws Exception {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setPackagesToScan("org.xezz.timeregistration.model.Coworker",
                "org.xezz.timeregistration.model.Customer",
                "org.xezz.timeregistration.model.Project",
                "org.xezz.timeregistration.model.TimeSpan");
        entityManagerFactory.setPersistenceUnitName("timeregistration");
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter());
        entityManagerFactory.setJpaDialect(new OpenJpaDialect());
        final Properties jpaProperties = new Properties();
        jpaProperties.setProperty("openjpa.jdbc.SynchronizeMappings", "buildSchema(ForeignKeys=true)");
        //jpaProperties.setProperty("openjpa.Log", "SQL=TRACE");
        jpaProperties.setProperty("openjpa.InitializeEagerly", "true");
        jpaProperties.setProperty("openjpa.RuntimeUnenhancedClasses", "supported");
        entityManagerFactory.setJpaProperties(jpaProperties);
        //entityManagerFactory.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");

        return entityManagerFactory;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        OpenJpaVendorAdapter jpaVendorAdapter = new OpenJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setDatabase(Database.HSQL);

        return jpaVendorAdapter;
    }

    @Bean
    public JpaTransactionManager transactionManager() throws Exception {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        transactionManager.setPersistenceUnitName("timeregistration");

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {

        return new PersistenceExceptionTranslationPostProcessor();
    }
}
