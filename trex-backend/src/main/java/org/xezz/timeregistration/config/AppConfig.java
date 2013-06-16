package org.xezz.timeregistration.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * User: Xezz
 * Date: 12.05.13
 * Time: 19:48
 * Wire up all Spring java-based configurations
 */
@EnableTransactionManagement
@ComponentScan("org.xezz.timeregistration")
// Disabled webconfig for now, as it should get loaded from the context
@Import({DataConfig.class/*, WebConfig.class*/})
@Configuration
@EnableSpringConfigured
// Set this a default profile, so it is possible to load different profiles if need be
@Profile("default")
public class AppConfig {
}
