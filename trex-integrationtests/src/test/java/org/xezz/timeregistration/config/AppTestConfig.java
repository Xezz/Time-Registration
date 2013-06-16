package org.xezz.timeregistration.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * User: Xezz
 * Date: 12.06.13
 * Time: 23:18
 * Spring based bootstrap configuration for the whole application
 */
@EnableTransactionManagement
@ComponentScan({"org.xezz.timeregistration.service", "org.xezz.timeregistration.model", "org.xezz.timeregistration.dao"})
@EnableJpaRepositories("org.xezz.timeregistration.repository")
// Disabled webconfig for now, as it should get loaded from the context
@Import({DataTestConfig.class})
@Configuration
@EnableSpringConfigured
@Profile("integration-test")
public class AppTestConfig {
}
