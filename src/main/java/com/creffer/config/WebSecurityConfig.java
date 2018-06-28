package com.creffer.config;

import com.creffer.security.CustomLogoutSuccessHandler;
import com.creffer.config.security.UserDetailsServiceImpl;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.sql.DataSource;


@Configuration
@ComponentScan({"com.creffer"})
@EnableWebSecurity
//@EnableOAuth2Sso
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    /*@Autowired
    private UserDetailsServiceImpl userDetailsService;*/
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
                .authorizeRequests()
                .antMatchers("/admin/**").permitAll()//.hasRole("ADMIN")
                .antMatchers("/manager/**").hasRole("MANAGER")
                .antMatchers("/publisherDashboard").permitAll() //hasRole("PUBLISHER")
                .antMatchers("/adminDashboard").permitAll() //hasRole("PUBLISHER")
                .antMatchers("/advertiser/**").hasRole("ADVERTISER")
                .antMatchers("/anonymous/**").anonymous()
                .antMatchers("/login").permitAll()
                .antMatchers("/signup").permitAll()

                .antMatchers("/imgs/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/main").permitAll()
                .antMatchers("/track").permitAll()
                .antMatchers("/doGame").permitAll()
                .anyRequest().permitAll().and();
        http.formLogin()
                .loginPage("/login")
                //.loginProcessingUrl("/adminDashboard")
                //.failureUrl("/login?error=true")
                //.usernameParameter("j_email")
                //.passwordParameter("j_password")
                .permitAll();

        http.logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(logoutSuccessHandler())
                .invalidateHttpSession(true);
    }
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(){
        return new CustomLogoutSuccessHandler();
    }

    /*@Bean
    public UserDetailsServiceImpl getUserDetailsService(){
        return new UserDetailsServiceImpl();
    }*/

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
                //.userDetailsService(userDetailsService)
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
