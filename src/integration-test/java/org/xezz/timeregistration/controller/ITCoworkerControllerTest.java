package org.xezz.timeregistration.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.xezz.timeregistration.config.AppTestConfig;
import org.xezz.timeregistration.config.DataTestConfig;
import org.xezz.timeregistration.config.WebTestConfig;

/**
 * User: Xezz
 * Date: 12.06.13
 * Time: 21:33
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {AppTestConfig.class, DataTestConfig.class, WebTestConfig.class})
@ActiveProfiles("integration-test")
public class ITCoworkerControllerTest {

    // Injected context
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(wac).build();
    }

    @Test
    public void testGetAll() throws Exception {

    }

    @Test
    public void testGet() throws Exception {

    }

    @Test
    public void testGetByFirstName() throws Exception {

    }

    @Test
    public void testGetByLastName() throws Exception {

    }

    @Test
    public void testCreate() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testList() throws Exception {

    }

    @Test
    public void testShowById() throws Exception {

    }

    @Test
    public void testShowByFirstName() throws Exception {

    }

    @Test
    public void testShowByLastName() throws Exception {

    }
}
