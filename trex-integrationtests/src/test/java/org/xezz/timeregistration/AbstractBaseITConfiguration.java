package org.xezz.timeregistration;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.xezz.timeregistration.config.AppTestConfig;
import org.xezz.timeregistration.config.DataTestConfig;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * User: Xezz
 * Date: 03.07.13
 * Time: 17:01
 * Abstract class to use for integration testing.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {AppTestConfig.class, DataTestConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@ActiveProfiles("integration-test")
@Configurable
@DatabaseSetup("/META-INF/basicdata.xml")
public abstract class AbstractBaseITConfiguration {
    // Required by DBUnit
    @Resource
    DataSource dataSource;
}
