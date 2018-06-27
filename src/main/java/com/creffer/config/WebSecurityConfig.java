package com.creffer.config;

import com.creffer.security.CustomLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


@Configuration
@ComponentScan("com.creffer")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Profile("!https")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Value("${spring.queries.users-query}")
    private String usersQuery;
    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Override
    protected void configure(final HttpSecurity http) throws Exception{
        http
            .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/manager/**").hasRole("MANAGER")
                .antMatchers("/publisher/**").hasRole("PUBLISHER")
                .antMatchers("/advertiser/**").hasRole("ADVERTISER")
                .antMatchers("/anonymous/**").anonymous()
                .antMatchers("/login").permitAll()

                    .antMatchers("/imgs/**").permitAll()
                    .antMatchers("/css/**").permitAll()
                    .antMatchers("/js/**").permitAll()
                    .antMatchers("/").permitAll()
                    .antMatchers("/signup").permitAll()
                    .antMatchers("/main").permitAll()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/track").permitAll()
                    .antMatchers("/doGame").permitAll()

                    .anyRequest().authenticated().and().formLogin()
                    .loginPage("/login.html")
                    .loginProcessingUrl("/perform_login")
                    .defaultSuccessUrl("/publisher/dashboard.html")//TO DO
                    .failureUrl("/login?error=true")
                    .and().logout()
                    .logoutUrl("/main.html")
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessHandler(logoutSuccessHandler());
    }
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(){
        return new CustomLogoutSuccessHandler();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource())
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @ConfigurationProperties(prefix = "datasource.primary")
    @Bean
    @Primary
    public DataSource dataSource(){
        return DataSourceBuilder
                .create()
                .build();
    }
}
