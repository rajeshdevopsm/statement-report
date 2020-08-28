package com.assignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("testUser").password("{noop}userpassword").roles("USER")
                .and()
                .withUser("testadmin").password("{noop}adminpassword").roles("USER", "ADMIN");

    }

    // Secure the endpoins with HTTP Basic authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /*http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/default-statement-report/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/statement-report-daterange/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/statement-report-amountrange/**").hasRole("ADMIN")
                .and()
                .formLogin().loginPage("/login")
				.defaultSuccessUrl("/index")
				.failureUrl("/login?error")
				.usernameParameter("username").passwordParameter("password")				
			.and()
				.logout().logoutSuccessUrl("/login?logout"); 
                //.csrf().disable()
                //.formLogin().disable(); */
    	http         
        .headers()
         .frameOptions().sameOrigin()
         .and()
           .authorizeRequests()
            .antMatchers("/resources/**", "/webjars/**","/assets/**").permitAll()
               .antMatchers("/").permitAll()
               //.antMatchers("/admin/**").hasRole("ADMIN")
               .antMatchers(HttpMethod.GET, "/statement/statement-report/**").hasRole("USER")
               .antMatchers(HttpMethod.GET, "/statement/statement-report-daterange/**").hasRole("ADMIN")
               .antMatchers(HttpMethod.GET, "/statement/statement-report-amountrange/**").hasRole("ADMIN")
               .antMatchers("/get-statement").hasRole("USER")
               .antMatchers("/get-statement-daterange").hasRole("ADMIN")
               .antMatchers("/get-statement-amountrange").hasRole("ADMIN")
               .anyRequest().authenticated()
               .and()
           .formLogin()
               .loginPage("/login")
               .defaultSuccessUrl("/index")
               .failureUrl("/login?error")
               .permitAll()
               .and()
               .logout()
                   .invalidateHttpSession(true)
                   .clearAuthentication(true)
                   .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                   .logoutSuccessUrl("/login?logout")
                       .permitAll()
           .and()
               .rememberMe()
                   .key("unique-and-secret")
                   .rememberMeCookieName("remember-me-cookie-name")
                   .tokenValiditySeconds(86400)
                   .and()
    	.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        	.and()
    	.sessionManagement().maximumSessions(1)
    	.and()
    	.invalidSessionUrl("/?sessionexpired=true");;

    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}