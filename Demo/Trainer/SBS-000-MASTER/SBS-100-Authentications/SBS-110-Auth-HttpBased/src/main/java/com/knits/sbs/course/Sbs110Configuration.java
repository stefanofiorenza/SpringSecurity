package com.knits.sbs.course;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.knits.sbs.course.security.CustomAuthenticationFailureHandler;
import com.knits.sbs.course.security.CustomLogoutSuccessHandler;

@EnableWebSecurity
@Configuration
public class Sbs110Configuration extends WebSecurityConfigurerAdapter{

	
	@Override
    protected void configure(AuthenticationManagerBuilder authorizationBuilder) throws Exception { 
		authorizationBuilder.inMemoryAuthentication()
        .withUser("stefano").password(passwordEncoder().encode("newPassword"))
        .authorities("manager");
    }
    
    
    @Override
    protected void configure(HttpSecurity authorizationBuilder) throws Exception { 
    	customizeLogin(setupAuthorizationOnUriPath(authorizationBuilder));
    }
    
    
	private HttpSecurity setupAuthorizationOnUriPath(HttpSecurity authorizationBuilder) throws Exception{
    	
    	return authorizationBuilder.authorizeRequests()    	
			.anyRequest().authenticated().
				antMatchers("/manager").hasRole("manager")
			.anyRequest().permitAll().and();
    }
    

    private void customizeLogin(HttpSecurity authorizationConfig) throws Exception {
    	authorizationConfig
    	.formLogin()
    	.loginPage("auth/login")
    	.loginProcessingUrl("/perform_login")
        .defaultSuccessUrl("/", true)
        .failureUrl("auth/denied")
        .failureHandler(authenticationFailureHandler())
        .and()
        .logout()
        .logoutUrl("/auth/logout")
        .deleteCookies("JSESSIONID")
        .logoutSuccessHandler(logoutSuccessHandler());
    	
    }
    
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
		return new CustomLogoutSuccessHandler();
	}


    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
		return new CustomAuthenticationFailureHandler();
	}

    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
	
}
