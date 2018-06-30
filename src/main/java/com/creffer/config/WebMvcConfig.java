package com.creffer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
//@ComponentScan() - ?
public class WebMvcConfig extends WebMvcConfigurerAdapter implements WebMvcConfigurer{
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/resources/",
            "classpath:/static/"};
    @Autowired
    @Qualifier("jstlViewResolver")
    private ViewResolver jstlViewResolver;
    public WebMvcConfig(){
        super();
    }
    @Bean
    @DependsOn({ "jstlViewResolver" })
    public ViewResolver viewResolver() {
        return jstlViewResolver;
    }

    @Bean(name = "jstlViewResolver")
    public ViewResolver getViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/pages/");
        resolver.setSuffix(".html");
        return resolver;
    }
    @Bean(name = "bCryptPasswordEncoder")
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS)
                .setCachePeriod(31556926);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
        /*registry.addViewController("/pages/login.html").setViewName("/login");
        registry.addViewController("/pages/home.html").setViewName("/home");
        registry.addViewController("/pages/signup.html").setViewName("/signup");
        registry.addViewController("/pages/protected/publisher/dashboard.html").setViewName("/publisherDashboard");
        registry.addViewController("/pages/protected/admin/dashboard.html").setViewName("/adminDashboard");
        registry.addViewController("/pages/protected/advertiser/dashboard.html").setViewName("/advertiserDashboard");
        registry.addViewController("/pages/protected/manager/dashboard.html").setViewName("/managerDashboard");*/
    }

}
