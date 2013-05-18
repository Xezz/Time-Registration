package org.xezz.timeregistration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Xezz
 * Date: 16.05.13
 * Time: 10:11
 * Configure MVC
 */
@Configuration
@ComponentScan("org.xezz.timeregistration.controller")
@EnableWebMvc
public class WebConfig {

    @Bean
    public ContentNegotiatingViewResolver viewResolver() {
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(contentNegotiationManager());
        resolver.setViewResolvers(viewResolvers());
        resolver.setDefaultViews(defaultViews());

        return resolver;
    }

    @Bean
    public ContentNegotiationManager contentNegotiationManager() {

        return new ContentNegotiationManager();
    }

    @Bean
    public List<ViewResolver> viewResolvers() {
        List<ViewResolver> views = new ArrayList<ViewResolver>();
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");

        views.add(resolver);
        return views;
    }

    @Bean
    public List<View> defaultViews() {
        List<View> views = new ArrayList<View>();
        MappingJacksonJsonView jacksonJsonView = new MappingJacksonJsonView();
        // TODO: If validation of JSON fails, set prefix to false
        jacksonJsonView.setPrefixJson(true);

        views.add(jacksonJsonView);
        return views;
    }
}