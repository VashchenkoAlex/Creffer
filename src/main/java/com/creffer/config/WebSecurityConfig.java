package com.creffer.config;

import com.creffer.security.CustomLogoutSuccessHandler;
import com.creffer.security.access_handlers.CrefDeniedHandler;
import com.creffer.security.access_vouters.CrefVoter;
import com.creffer.services.user.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;


@Configuration
@ComponentScan({"com.creffer"})
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    //@Autowired
    //CrefSuccessHandler crefSuccessHandler;
    @Autowired
    private AccessDeniedHandler deniedHandler;
    /*@Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;*/

    /*@Autowired
    private TokenAuthenticationManager tokenAuthenticationManager;
    @Autowired
    private AuthenticationProvider tokenAuthProvider;*/

    @Autowired
    SecurityContextHolder securityContextHolder;
    @Autowired
    AccessDecisionManager decisionManager;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Value("${spring.queries.users-query}")
    private String usersQuery;
    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Override
    protected void configure(final HttpSecurity http) throws Exception{
        http.csrf()
                .disable()
                .headers().frameOptions().sameOrigin()
                .and()
                .exceptionHandling().accessDeniedHandler(deniedHandler);
                /*.and()
                .addFilterAfter(restTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);*/
        http.authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/javaAngular/**").permitAll()
            .antMatchers("/signup").permitAll()
            .antMatchers("/login").permitAll()
            .antMatchers("/js/**").permitAll()
            .antMatchers("/main").permitAll()
            .antMatchers("/track").permitAll()
            .antMatchers("/doGame").permitAll()
            .antMatchers("/pages/protected/manager/**").hasRole("MANAGER")
                .antMatchers("/managerDashboard").hasRole("MANAGER")
                .antMatchers("/adminDashboard").hasRole("ADMIN")
                .antMatchers("/pages/protected/admin/**").hasRole("ADMIN")
                .antMatchers("/publisherDashboard").hasRole("PUBLISHER")
                .antMatchers("/pages/protected/publisher/**").hasRole("PUBLISHER")
            .antMatchers("/protected/**").authenticated().accessDecisionManager(decisionManager);
        /*http.formLogin()
                .loginPage("/login")
                .successHandler(new CrefSuccessHandler())
                .failureHandler(new CrefFailureHandler());*/
    }
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(){
        return new CustomLogoutSuccessHandler();
    }

    /*@Bean(name = "restTokenAuthenticationFilter")
    public RestTokenAuthenticationFilter restTokenAuthenticationFilter() {
        RestTokenAuthenticationFilter restTokenAuthenticationFilter = new RestTokenAuthenticationFilter();
        tokenAuthenticationManager.setUserDetailsService(userDetailsService);
        restTokenAuthenticationFilter.setAuthenticationManager(tokenAuthenticationManager);
        return restTokenAuthenticationFilter;
    }*/

    @Bean(name = "userDetailsService")
    public UserDetailsService getUserDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth//.parentAuthenticationManager(tokenAuthenticationManager)
                //.authenticationProvider(tokenAuthProvider)
                .userDetailsService(new UserDetailsServiceImpl()/*userDetailsService*/)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "datasource.primary")
    public DataSourceProperties firstDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "datasource.primary")
    public DataSource dataSource(){
        return firstDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    public SecurityContextHolder securityContextHolder(){
        return new SecurityContextHolder();
    }
    @Bean
    public AccessDeniedHandler crefDeniedHandler(){
        return new CrefDeniedHandler();
    }

    @Bean
    public AccessDecisionManager accessDecisionManager(){
        List<AccessDecisionVoter<? extends Object>> decisionVoters
                = Arrays.asList(
                        new WebExpressionVoter(),
                        new RoleVoter(),
                        new AuthenticatedVoter(),
                        new CrefVoter());
                return new UnanimousBased(decisionVoters);
    }
}
